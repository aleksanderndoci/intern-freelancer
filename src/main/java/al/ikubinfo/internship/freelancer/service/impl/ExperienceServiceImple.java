package al.ikubinfo.internship.freelancer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.repository.ExperienceRepository;
import al.ikubinfo.internship.freelancer.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ExperienceServiceImple implements ExperienceService {

	@Autowired
	private final ExperienceRepository repository;

	private final Mapper<Experience, ExperienceModel> expMapper;

	public ExperienceServiceImple(Mapper<Experience, ExperienceModel> expMapper,
			ExperienceRepository experinceRepository) {
		this.repository = experinceRepository;
		this.expMapper = expMapper;
	}

	@Override
	public ExperienceModel addOrUpdate(ExperienceModel experienceModel) {
		Experience expEntity = expMapper.toEntity(experienceModel);
		repository.save(expEntity);
		return expMapper.toModel(expEntity);
	}

	@Override
	public List<ExperienceModel> addExperiences(List<ExperienceModel> experienceModelList) {
		List<Experience> experienceEntityList = expMapper.toEntityList(experienceModelList);
		repository.saveAll(experienceEntityList);
		return expMapper.toModelList(experienceEntityList);
	}

	@Override
	public String deleteExperience(int id) {
		Experience experience = repository.getOne(id);
		repository.delete(experience);
		return String.format("Experience with %d , position %s at company %s IS DELETED", id, experience.getPosition(),
				experience.getNameOfCompany());

	}//handle exception if an id does not exists

	@Override
	public List<ExperienceModel> getExperiencesByUserId(int userId) {
		List<Experience> experienceEntityList = repository.findByUserId(userId);
		return expMapper.toModelList(experienceEntityList);
	}

	
}