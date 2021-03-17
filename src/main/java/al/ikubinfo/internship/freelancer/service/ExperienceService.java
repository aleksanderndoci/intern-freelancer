package al.ikubinfo.internship.freelancer.service;

import java.util.List;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;

public interface ExperienceService {

	ExperienceModel addOrUpdate(ExperienceModel experienceModel);

	List<Experience> addExperiences(List<Experience> experiences);

	String deleteExperience(int id);
	
	List<Experience> getExperiencesByUserId(int id);
	
	

}
