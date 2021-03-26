package al.ikubinfo.internship.freelancer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
@Validated

public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@PostMapping(path = "addExperience")
	@PreAuthorize("hasAuthority('FREELANCER')")
	public ResponseEntity<ExperienceModel> addExperience(@RequestBody ExperienceModel experienceModel) {
		return new ResponseEntity<>(experienceService.addExperience(experienceModel), HttpStatus.CREATED);
	}

	@PutMapping(path = "updateExperience/{id}")
	@PreAuthorize("hasAuthority('FREELANCER')")
	public ResponseEntity<ExperienceModel> updateExperience(@RequestBody ExperienceModel experienceModel) {
		return new ResponseEntity<>(experienceService.updateExperience(experienceModel), HttpStatus.OK);
	}

	@DeleteMapping(path = "deleteExperience/{id}")
	@PreAuthorize("hasAuthority('FREELANCER')")
	public ResponseEntity<HttpStatus> deleteExperience(@PathVariable("id") Integer id) {
		experienceService.deleteExperience(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "getExperiences/{userId}")
	@PreAuthorize("hasAnyAuthority('FREELANCER','EMPLOYER')")
	public ResponseEntity<List<ExperienceModel>> getExpersByUserId(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(experienceService.getExperiencesByUserId(userId));
	}
}
