package al.ikubinfo.internship.freelancer.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;

public interface ExperienceService {

	ExperienceModel addExperience(ExperienceModel experienceModel);

	ExperienceModel updateExperience(ExperienceModel experienceModel);

	List<ExperienceModel> addExperiences(List<ExperienceModel> experienceModelList);

	HttpStatus deleteExperience(int id);

	List<ExperienceModel> getExperiencesByUserId(int id);
}
