package al.ikubinfo.internship.freelancer.mapper.impl;

import org.springframework.stereotype.Component;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;

@Component
public class RegistrationRequestMapperImpl implements Mapper<User, RegistrationRequest> {

	@Override
	public User toEntity(RegistrationRequest model) {
		User entity = new User();
		entity.setName(model.getName());
		entity.setSurname(model.getSurname());
		entity.setEmail(model.getEmail());
		entity.setPassword(model.getPassword());
		entity.setRole(model.getRole());
		return entity;
	}

	@Override
	public RegistrationRequest toModel(User entity) {

		RegistrationRequest registerRequest = new RegistrationRequest(
				entity.getName(),
				entity.getSurname(),
			    entity.getEmail(),
			    entity.getPassword(),
			    entity.getRole());
		return registerRequest;
		
				
	}

}
