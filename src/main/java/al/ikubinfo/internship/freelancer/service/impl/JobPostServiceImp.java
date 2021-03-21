package al.ikubinfo.internship.freelancer.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.Role;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.repository.JobPostRepository;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.JobPostService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JobPostServiceImp implements JobPostService {

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private UserRepository userRepository;

	private Mapper<JobPost, JobPostModel> jobPostmapper;

	public JobPostServiceImp(JobPostRepository jobPostRepository, Mapper<JobPost, JobPostModel> mapper) {
		super();
		this.jobPostRepository = jobPostRepository;
		this.jobPostmapper = mapper;
	}

	@Override
	public JobPostModel addJobPost(JobPostModel jobPostModel) {
		try {
			JobPost jobPostEntity = jobPostmapper.toEntity(jobPostModel);
			User user = userRepository.findById(jobPostModel.getUserModel().getId())
					.orElseThrow(() -> new ResourceNotFoundException("unvalid user"));
			if (Role.FREELANCER.equals(user.getRole())) {
				jobPostEntity.setJobPostType("JOB DEMAND");
			}
			if (Role.EMPLOYER.equals(user.getRole())) {
				jobPostEntity.setJobPostType("JOB OFFER");

			}
			jobPostEntity.setUser(user);
			jobPostEntity
					.setPostDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
			jobPostRepository.save(jobPostEntity);

			return jobPostmapper.toModel(jobPostEntity);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}

	@Override
	public JobPostModel updateJobPost(JobPostModel jobPostModel) {
		try {
			JobPost entity = jobPostmapper.toEntity(jobPostModel);

			JobPost jobPostById = jobPostRepository.findById(jobPostModel.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Not found job post with id " + jobPostModel.getId()));

			entity.setUpdateDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            entity.setPostDate(jobPostById.getPostDate());
            entity.setJobPostType(jobPostById.getJobPostType());
            //entity.setUser(jobPostById.getUser());
            // It doesn't work.. necessary to add in postman 
            entity.setPosition(jobPostModel.getPosition());
            entity.setPositionDescription(jobPostModel.getPositionDescription());
            entity.setSalary(jobPostModel.getSalary());
            entity.setWorkingHour(jobPostModel.getWorkingHour());
            
			jobPostRepository.save(entity);
			return jobPostmapper.toModel(entity);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}

	}

	//why time has 1 hour diff compared to actual one in LocalDateTime.now
	// in IDE for Java developer it's okay
	//should i change data type in postgres
}
