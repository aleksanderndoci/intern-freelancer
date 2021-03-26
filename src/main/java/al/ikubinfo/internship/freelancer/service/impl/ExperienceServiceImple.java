package al.ikubinfo.internship.freelancer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.repository.ExperienceRepository;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.ExperienceService;

@Service
@Transactional

public class ExperienceServiceImple implements ExperienceService {

	@Autowired
	private final ExperienceRepository experienceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private final Mapper<Experience, ExperienceModel> expMapper;

	public ExperienceServiceImple(Mapper<Experience, ExperienceModel> expMapper,
			ExperienceRepository experinceRepository) {
		this.experienceRepository = experinceRepository;
		this.expMapper = expMapper;
	}

	@Override
	public ExperienceModel addExperience(ExperienceModel experienceModel) {
		Experience expEntity = expMapper.toEntity(experienceModel);
		User user = userRepository.findById(experienceModel.getUserModel().getId())
				.orElseThrow(() -> new ResourceNotFoundException("unvalid user"));
		if (experienceModel.getEndDate().after(experienceModel.getStartDate())) {
			expEntity.setUser(user);
			experienceRepository.save(expEntity);
			return expMapper.toModel(expEntity);
		}else {
			throw new ResourceNotFoundException("End date should be after start date");
		}
	}

	@Override
	public ExperienceModel updateExperience(ExperienceModel experienceModel) {
		Experience expEntity = expMapper.toEntity(experienceModel);
		Experience expById = experienceRepository.findById(experienceModel.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Not found experience with id " + experienceModel.getId()));

		if (expById.getUser().getId() == experienceModel.getUserModel().getId()) {
			expEntity.setId(expById.getId());
			expEntity.setNameOfCompany(experienceModel.getNameOfCompany());
			expEntity.setPosition(experienceModel.getPosition());
			expEntity.setPositionDescription(experienceModel.getPositionDescription());
			expEntity.setStartDate(experienceModel.getStartDate());
			expEntity.setEndDate(experienceModel.getEndDate());
			expEntity.setUser(expById.getUser());

			experienceRepository.save(expEntity);
			return expMapper.toModel(expEntity);
		} else {
			throw new ResourceNotFoundException(String.format("user with id %d has not experience with id %d",
					experienceModel.getUserModel().getId(), expById.getId()));		}
	}

	@Override
	public HttpStatus deleteExperience(Integer id) {
		Experience experience = experienceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("invalid id"));

		experienceRepository.delete(experience);
		return HttpStatus.NO_CONTENT;
	}

	@Override
	public List<ExperienceModel> getExperiencesByUserId(int userId) {
		List<Experience> experienceEntityList = experienceRepository.findByUserId(userId);
		if (experienceEntityList.isEmpty()) {
			throw new ResourceNotFoundException(String.format("No user with id %d. ", userId));
		}
		return expMapper.toModelList(experienceEntityList);
	}
}
