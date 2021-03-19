package al.ikubinfo.internship.freelancer.mapper.impl;

import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<User, UserModel> {

	@Override
	public User toEntity(UserModel model) {
		User userEntity = new User();
		userEntity.setName(model.getName());
		userEntity.setSurname(model.getSurname());
		userEntity.setEmail(model.getEmail());
		userEntity.setPassword(model.getPassword());
		userEntity.setCountry(model.getCountry());
		userEntity.setBirthday(model.getBirthday());
		userEntity.setActivationStatus(model.getActivationStatus());
		userEntity.setRole(model.getRole());
//		userEntity.setExperiences(model.getExperiences());
//		userEntity.setEducation(model.getEducation());
//		userEntity.setJobPosts(model.getJobPosts());
		userEntity.setJobPostsApplication(model.getJobPostApplications());
		return userEntity;
	}

//
	@Override
	public UserModel toModel(User entity) {
		UserModel userModel = new UserModel();
		userModel.setName(entity.getName());
		userModel.setSurname(entity.getSurname());
		userModel.setEmail(entity.getEmail());
		userModel.setPassword(entity.getPassword());
		userModel.setCountry(entity.getCountry());
		userModel.setBirthday(entity.getBirthday());
		userModel.setActivationStatus(entity.getActivationStatus());
		userModel.setRole(entity.getRole());
       // userModel.setExperiences(entity.getExperiences());
//		userModel.setEducation(entity.getEducation());
//		userModel.setJobPosts(entity.getJobPosts());
		userModel.setJobPostApplications(entity.getJobPostsApplication());

		return userModel;
	}
}
