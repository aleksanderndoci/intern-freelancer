package al.ikubinfo.internship.freelancer.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.Role;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.model.ListJobPostBy;
import al.ikubinfo.internship.freelancer.repository.JobPostRepository;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.JobPostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class JobPostServiceImp implements JobPostService {

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Mapper<JobPost, JobPostModel> jobPostmapper;

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
			jobPostEntity.setPostDate(new Date());
			jobPostRepository.save(jobPostEntity);

			return jobPostmapper.toModel(jobPostEntity);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	@Override
	public JobPostModel updateJobPost(JobPostModel jobPostModel) {
		JobPost entity = jobPostmapper.toEntity(jobPostModel);

		JobPost jobPostById = jobPostRepository.findById(jobPostModel.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found job post with id " + jobPostModel.getId()));

		if (jobPostById.getUser().getId() == jobPostModel.getUserModel().getId()) {
			entity.setUpdateDate(new Date());
			entity.setPostDate(jobPostById.getPostDate());
			entity.setJobPostType(jobPostById.getJobPostType());
			entity.setUser(jobPostById.getUser());
			entity.setPosition(jobPostModel.getPosition());
			entity.setPositionDescription(jobPostModel.getPositionDescription());
			entity.setSalary(jobPostModel.getSalary());
			entity.setWorkingHour(jobPostModel.getWorkingHour());
			jobPostRepository.save(entity);
			return jobPostmapper.toModel(entity);

		} else {
			throw new ResourceNotFoundException(String.format("user with id %d has not posted job post with id %d",
					jobPostModel.getUserModel().getId(), jobPostById.getId()));
		}

	}

	@Override
	public List<JobPostModel> listJobPostsByUserId(int userId) {
		List<JobPost> jobPostsEntityList = jobPostRepository.findByUserId(userId);
		if (jobPostsEntityList.isEmpty()) {
			throw new ResourceNotFoundException("Write a valid id");
		}
		return jobPostmapper.toModelList(jobPostsEntityList);

	}

	@Override
	public HttpStatus deleteJobPost(Integer id) {
		JobPost jobPost = jobPostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("enter a valid experience id"));
		jobPostRepository.delete(jobPost);
		return HttpStatus.NO_CONTENT;

	}

	@Override
	public List<JobPostModel> findByPosition(ListJobPostBy listBy) {

		Pageable paging = PageRequest.of(listBy.getPageNr(), listBy.getPageSize(),
				Sort.by(listBy.getSortBy()).ascending());

		List<JobPost> jobPostEntityList = jobPostRepository.findByJobPostTypeAndPosition(listBy.getType(),
				listBy.getPosition(), paging);

		if(jobPostEntityList.isEmpty()) {
			throw new ResourceNotFoundException("No result for that search");

		}
		log.info(jobPostEntityList.toString());
		return jobPostmapper.toModelList(jobPostEntityList);

	}
}
