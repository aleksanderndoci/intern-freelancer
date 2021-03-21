package al.ikubinfo.internship.freelancer.mapper.impl;

import org.springframework.stereotype.Component;
import al.ikubinfo.internship.freelancer.entity.Application;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.ApplicationModel;


@Component
public class ApplicationMapperImpl implements Mapper<Application, ApplicationModel> {
	@Override
	public Application toEntity(ApplicationModel model) {
		Application appEntity = new Application();
		appEntity.setId(model.getAppKeyId());
		appEntity.setApplicationStatus(model.getApplicationStatus());
		appEntity.setApplicationDate(model.getApplicationDate());
		appEntity.setAcceptDate(model.getAcceptDate());
		appEntity.setRefuseDate(model.getRefuseDate());
		return appEntity;
	}

	@Override
	public ApplicationModel toModel(Application entity) {
		ApplicationModel applicationModel = new ApplicationModel();
		applicationModel.setAppKeyId(entity.getId());
		applicationModel.setApplicationStatus(entity.getApplicationStatus());
		applicationModel.setApplicationDate(entity.getApplicationDate());
		applicationModel.setAcceptDate(entity.getAcceptDate());
		applicationModel.setRefuseDate(entity.getRefuseDate());
		return applicationModel;
	}

}
