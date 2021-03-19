package al.ikubinfo.internship.freelancer.service;

import java.util.List;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;

public interface ExperienceService {

	ExperienceModel updateExperience(Integer id,ExperienceModel experienceModel);

	List<ExperienceModel> addExperiences(List<ExperienceModel> experienceModelList);
	
	String deleteExperience(int id);
	
	List<ExperienceModel> getExperiencesByUserId(int id);
	
	ExperienceModel addExperience(ExperienceModel experienceModel);

}
