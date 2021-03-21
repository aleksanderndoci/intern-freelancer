package al.ikubinfo.internship.freelancer.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ApplicationServiceImpl implements ApplicationService { // Application Model returned - null? works with application entity

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

	private Mapper<Application, ApplicationModel> applicationMapper;

	@Override
	public ApplicationModel apply(ApplicationKey primaryKey) {
		try {

			Application application = new Application();

			JobPost jobPost = jobPostRepository.findById(primaryKey.getJobPostId())
					.orElseThrow(() -> new ResourceNotFoundException("unvalid job post"));

			User user = userRepository.findById(primaryKey.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException("unvalid user"));

			if(applicationRepository.findById(primaryKey).isPresent()) {
				 new ResourceNotFoundException("You have already applied once!");
			}
			//else{} null id exception
			if (jobPost.getJobPostType().equals("JOB DEMAND") && Role.EMPLOYER.equals(user.getRole())
					|| jobPost.getJobPostType().equals("JOB OFFER") && Role.FREELANCER.equals(user.getRole())) {
				application.setId(primaryKey);
				application.setApplicationDate(
						java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
				application.setApplicationStatus("APPLIED! PENDING!");
			}
			
			applicationRepository.save(application);
			return applicationMapper.toModel(application);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}

	}

	public ApplicationModel getApplicationByPK(ApplicationKey key) {
		try {
			Application appEntity = applicationRepository.findById(key)
					.orElseThrow(() -> new ResourceNotFoundException("unvalid PK"));
			return applicationMapper.toModel(appEntity);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}

	}
}