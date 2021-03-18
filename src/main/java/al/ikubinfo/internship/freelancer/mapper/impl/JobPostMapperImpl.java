package al.ikubinfo.internship.freelancer.mapper.impl;

import org.springframework.stereotype.Component;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.JobPostModel;

@Component
public class JobPostMapperImpl implements Mapper<JobPost, JobPostModel> {

	@Override
	public JobPost toEntity(JobPostModel model) {

		JobPost jobPost = new JobPost();
		jobPost.setId(model.getId());
		jobPost.setPosition(model.getPosition());
		jobPost.setPositionDescription(model.getPositionDescription());
		jobPost.setSalary(model.getSalary());
		jobPost.setWorkingHour(model.getWorkingHour());
		jobPost.setJobPostDate(model.getJobPostDate());
		jobPost.setJobPostType(model.getJobPostType());
		jobPost.setUser(model.getUser());
		return jobPost;
	}

	@Override
	public JobPostModel toModel(JobPost entity) {
		JobPostModel model = new JobPostModel();
		model.setId(entity.getId());
		model.setPosition(entity.getPosition());
		model.setPositionDescription(entity.getPositionDescription());
		model.setSalary(entity.getSalary());
		model.setWorkingHour(entity.getWorkingHour());
		model.setJobPostDate(entity.getJobPostDate());
		model.setJobPostType(entity.getJobPostType());
		model.setUser(entity.getUser());
		return model;
	}

}
