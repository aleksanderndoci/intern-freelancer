package al.ikubinfo.internship.freelancer.mapper.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.model.UserModel;

@Component
public class ExperienceMapperImpl implements Mapper<Experience, ExperienceModel> {

	@Autowired
	private Mapper<User, UserModel> userMapper;

	@Override
	public Experience toEntity(ExperienceModel model) {
		Experience experience = new Experience();
		experience.setId(model.getId());
		experience.setNameOfCompany(model.getNameOfCompany());
		experience.setPosition(model.getPosition());
		experience.setPositionDescription(model.getPositionDescription());
		experience.setStartDate(model.getStartDate());
		experience.setEndDate(model.getEndDate());

		User user = userMapper.toEntity(model.getUserModel());
		experience.setUser(user);

		return experience;
	}

	@Override
	public ExperienceModel toModel(Experience entity) {

		ExperienceModel experienceModel = new ExperienceModel();
		experienceModel.setId(entity.getId());
		experienceModel.setNameOfCompany(entity.getNameOfCompany());
		experienceModel.setPosition(entity.getPosition());
		experienceModel.setPositionDescription(entity.getPositionDescription());
		experienceModel.setStartDate(entity.getStartDate());
		experienceModel.setEndDate(entity.getEndDate());

		UserModel userModel = userMapper.toModel(entity.getUser());
		experienceModel.setUserModel(userModel);

		return experienceModel;
	}

}
