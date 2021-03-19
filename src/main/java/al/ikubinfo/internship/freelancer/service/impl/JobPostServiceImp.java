package al.ikubinfo.internship.freelancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.Role;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.repository.JobPostRepository;
import al.ikubinfo.internship.freelancer.service.JobPostService;
import al.ikubinfo.internship.freelancer.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JobPostServiceImp implements JobPostService {

	@Autowired
	private JobPostRepository jobPostRepository;

	private Mapper<JobPost, JobPostModel> mapper;

	public JobPostServiceImp(JobPostRepository jobPostRepository, Mapper<JobPost, JobPostModel> mapper) {
		super();
		this.jobPostRepository = jobPostRepository;
		this.mapper = mapper;
	}

	@Override
	public JobPostModel addJobPost(JobPostModel jobPostModel) {
		JobPost jobPostEntity = mapper.toEntity(jobPostModel);
		jobPostRepository.save(jobPostEntity);
		Role role = jobPostEntity.getUser().getRole();
		if (Role.FREELANCER.equals(role)) {
			jobPostEntity.setJobPostType("JOB DEMAND");
		}
		if (Role.EMPLOYER.equals(role)) {
			jobPostEntity.setJobPostType("JOB OFFER");

		}

		return mapper.toModel(jobPostEntity);
	}

	@Override
	public JobPostModel updateJobPost(Integer id, JobPostModel jobPostModel) {
		JobPost entity = mapper.toEntity(jobPostModel);
		
		JobPost jobPostById= jobPostRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Not found job post with id"+id));
	     
		entity.setId(jobPostById.getId());
		entity.setPosition(jobPostById.getPosition());
		entity.setPositionDescription(jobPostById.getPositionDescription());
		entity.setSalary(jobPostById.getSalary());
		entity.setUser(jobPostById.getUser());
		entity.setWorkingHour(jobPostById.getWorkingHour());
		entity.setJobPostDate(jobPostById.getJobPostDate());
		entity.setJobPostType(jobPostById.getJobPostType());

		jobPostRepository.save(entity);
		return mapper.toModel(entity);
	}

}
