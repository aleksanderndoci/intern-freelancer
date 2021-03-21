package al.ikubinfo.internship.freelancer.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.repository.ExperienceRepository;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ExperienceServiceImple implements ExperienceService {

	@Autowired
	private final ExperienceRepository experienceRepository;

	@Autowired
	private UserRepository userRepository;

	private final Mapper<Experience, ExperienceModel> expMapper;

	public ExperienceServiceImple(Mapper<Experience, ExperienceModel> expMapper,
			ExperienceRepository experinceRepository) {
		this.experienceRepository = experinceRepository;
		this.expMapper = expMapper;
	}

	@Override
	public ExperienceModel addExperience(ExperienceModel experienceModel) {
		try {
			Experience expEntity = expMapper.toEntity(experienceModel);
			User user = userRepository.findById(experienceModel.getUserModel().getId())
					.orElseThrow(() -> new ResourceNotFoundException("unvalid user"));
			expEntity.setUser(user);
			experienceRepository.save(expEntity);
			return expMapper.toModel(expEntity);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}

	@Override
	public ExperienceModel updateExperience(ExperienceModel experienceModel) {
		try {
			Experience expEntity = expMapper.toEntity(experienceModel);
			Experience expById = experienceRepository.findById(experienceModel.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Not found experience with id " + experienceModel.getId()));

			expEntity.setId(expById.getId());
			expEntity.setNameOfCompany(experienceModel.getNameOfCompany());
			expEntity.setPosition(experienceModel.getPosition());
			expEntity.setPositionDescription(experienceModel.getPositionDescription());
			expEntity.setStartDate(experienceModel.getStartDate());
			expEntity.setEndDate(experienceModel.getEndDate());
			expEntity.setUser(expById.getUser());

			experienceRepository.save(expEntity);
			return expMapper.toModel(expEntity);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}

	@Override
	public List<ExperienceModel> addExperiences(List<ExperienceModel> experienceModelList) {
		List<Experience> experienceEntityList = expMapper.toEntityList(experienceModelList);
		experienceRepository.saveAll(experienceEntityList);
		return expMapper.toModelList(experienceEntityList);
	}

	@Override
	public HttpStatus deleteExperience(int id) {
		try {
			Experience experience = experienceRepository.getOne(id);
			if (experience == null) {
				throw new ResourceNotFoundException("enter a valid experience id");
			}
			experienceRepository.delete(experience);
			return HttpStatus.NO_CONTENT;
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}

	@Override
	public List<ExperienceModel> getExperiencesByUserId(int userId) {
		try {
			List<Experience> experienceEntityList = experienceRepository.findByUserId(userId);
			if (experienceEntityList.isEmpty()) {
				throw new ResourceNotFoundException("NO CONTENT");
			}
			return expMapper.toModelList(experienceEntityList);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}
}
