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
		userEntity.setId(model.getId());
		userEntity.setName(model.getName());
		userEntity.setSurname(model.getSurname());
		userEntity.setEmail(model.getEmail());
		userEntity.setRole(model.getRole());
		return userEntity;
	}

	@Override
	public UserModel toModel(User entity) {
		UserModel userModel = new UserModel();
		userModel.setId(entity.getId());
		userModel.setName(entity.getName());
		userModel.setSurname(entity.getSurname());
		userModel.setEmail(entity.getEmail());
		userModel.setRole(entity.getRole());
		return userModel;
	}
}
