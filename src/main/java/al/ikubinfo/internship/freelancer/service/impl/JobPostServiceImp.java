package al.ikubinfo.internship.freelancer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.Role;
import al.ikubinfo.internship.freelancer.entity.User;
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
	
	@Autowired
	private UserService userService;

	private Mapper<JobPost, JobPostModel> mapper;

	public JobPostServiceImp(JobPostRepository jobPostRepository, Mapper<JobPost, JobPostModel> mapper) {
		super();
		this.jobPostRepository = jobPostRepository;
		this.mapper = mapper;
	}

	@Override
	public JobPostModel addOrUpdate(JobPostModel jobPostModel) {
		JobPost jobPostEntity = mapper.toEntity(jobPostModel);
		jobPostRepository.save(jobPostEntity);
       // User user = userService.findUserEById(jobPostEntity.getUser().getId());
		Role role = jobPostEntity.getUser().getRole();
		if (role.FREELANCER.name().equals("FREELANCER")) {
			jobPostEntity.setJobPostType("JOB DEMAND");
		}
		if (role.EMPLOYER.name().equals("EMPLOYEE")) {
			jobPostEntity.setJobPostType("JOB OFFER");

		}

		return mapper.toModel(jobPostEntity);
	}

}
