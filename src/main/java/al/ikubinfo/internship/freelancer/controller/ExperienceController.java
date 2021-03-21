package al.ikubinfo.internship.freelancer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.service.ExperienceService;

@RestController
@RequestMapping(path = "experience")
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@PostMapping(path = "addExperience")
	public ResponseEntity<ExperienceModel> addExperience(@RequestBody ExperienceModel experienceModel) {
		return new ResponseEntity<>(experienceService.addExperience(experienceModel), HttpStatus.CREATED);
	}

	@PutMapping(path = "updateExperience/{id}")
	public ResponseEntity<ExperienceModel> updateExperience(@RequestBody ExperienceModel experienceModel) {
		return new ResponseEntity<>(experienceService.updateExperience(experienceModel), HttpStatus.OK);
	}

	@PostMapping(path = "addExperiences")
	public ResponseEntity<List<ExperienceModel>> addExperiences(
			@RequestBody List<ExperienceModel> experiencesModelList) {
		return new ResponseEntity<>(experienceService.addExperiences(experiencesModelList), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "deleteExperience/{id}")
	public ResponseEntity<HttpStatus> deleteExperience(@PathVariable("id") int id) {
		experienceService.deleteExperience(id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "getExperiences/{userId}")
	public ResponseEntity<List<ExperienceModel>> getExpersByUserId(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(experienceService.getExperiencesByUserId(userId));
	}
}
