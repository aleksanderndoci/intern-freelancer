package al.ikubinfo.internship.freelancer.mapper.impl;

import org.springframework.stereotype.Component;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.LoginRequest;

@Component
public class LoginRequestMapperImpl implements Mapper<User, LoginRequest> {

	@Override
	public User toEntity(LoginRequest model) {
		User entity = new User();
		entity.setEmail(model.getEmail());
		entity.setPassword(model.getPassword());
		return entity;
	}

	@Override
	public LoginRequest toModel(User entity) {

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(entity.getEmail());
		loginRequest.setPassword(entity.getPassword());
		loginRequest.setLocked(entity.getLocked());
		return null;
	}

}
