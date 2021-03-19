package al.ikubinfo.internship.freelancer.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.model.UserModel;

@Component
public class JobPostMapperImpl implements Mapper<JobPost, JobPostModel> {

	@Autowired
	private Mapper<User, UserModel> userMapper;

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
		
		User user = userMapper.toEntity(model.getUserModel());
		jobPost.setUser(user);
		
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
		
		UserModel userModel = userMapper.toModel(entity.getUser());
		model.setUserModel(userModel);
		return model;
	}

}
