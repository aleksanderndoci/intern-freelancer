package al.ikubinfo.internship.freelancer.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import al.ikubinfo.internship.freelancer.entity.Application;
import al.ikubinfo.internship.freelancer.entity.ApplicationKey;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.Role;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.ApplicationModel;
import al.ikubinfo.internship.freelancer.repository.ApplicationRepository;
import al.ikubinfo.internship.freelancer.repository.JobPostRepository;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.ApplicationService;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private Mapper<Application, ApplicationModel> applicationMapper;

	@Override
	public ApplicationModel apply(ApplicationKey primaryKey) {
		Application application = new Application();

		JobPost jobPost = jobPostRepository.findById(primaryKey.getJobPostId())
				.orElseThrow(() -> new ResourceNotFoundException("unvalid job post"));

		User user = userRepository.findById(primaryKey.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("unvalid user"));

		if (applicationRepository.findById(primaryKey).isPresent()) {
			throw new ResourceNotFoundException("You have already applied once!");
		}
		if (jobPost.getJobPostType().equals("JOB DEMAND") && Role.EMPLOYER.equals(user.getRole())
				|| jobPost.getJobPostType().equals("JOB OFFER") && Role.FREELANCER.equals(user.getRole())) {
			application.setId(primaryKey);
			application.setApplicationDate(
					java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
			application.setApplicationStatus("APPLIED! PENDING!");
			applicationRepository.save(application);
			return applicationMapper.toModel(application);
		}else {
			throw new ResourceNotFoundException("unvalid user id");
		}
	}

	public ApplicationModel getApplicationByPK(ApplicationKey key) {
		Application appEntity = applicationRepository.findById(key)
				.orElseThrow(() -> new ResourceNotFoundException("unvalid PK"));
		return applicationMapper.toModel(appEntity);
	}

	@Override
	public ApplicationModel accept(ApplicationKey key) {
		Application appEntity = applicationRepository.findById(key)
				.orElseThrow(() -> new ResourceNotFoundException("unvalid PK"));
		appEntity.setAcceptDate(new Date());
		appEntity.setApplicationStatus("ACCEPTED");
		applicationRepository.save(appEntity);
		return applicationMapper.toModel(appEntity);
	}

	@Override
	public ApplicationModel refuse(ApplicationKey key) {
		Application appEntity = applicationRepository.findById(key)
				.orElseThrow(() -> new ResourceNotFoundException("unvalid PK"));
		appEntity.setRefuseDate(new Date());
		appEntity.setApplicationStatus("REFUSED");
		applicationRepository.save(appEntity);
		return applicationMapper.toModel(appEntity);
	}
}