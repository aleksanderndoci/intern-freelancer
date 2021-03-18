package al.ikubinfo.internship.freelancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.model.LoginRequest;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.model.UserModel;
import al.ikubinfo.internship.freelancer.service.ExperienceService;
import al.ikubinfo.internship.freelancer.service.JobPostService;
import al.ikubinfo.internship.freelancer.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "user")

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ExperienceService experienceService;
	
	@Autowired
	private JobPostService jobPostService;

	@PostMapping(path = "registration")
	@ResponseBody
	public RegistrationRequest register(@RequestBody RegistrationRequest request) {
		try {
			userService.register(request);
			return request;
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}// error handle

	@GetMapping(path = "registration/confirm")
	public String confirmEmail(@RequestParam("token") String token) {
		return userService.confirmToken(token);
	}

	@GetMapping(path = "login")
	public LoginRequest login(@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest);
	}

	@PutMapping(path = "addExperience")
	public ResponseEntity<ExperienceModel> addOrUpdateExperience(@RequestBody ExperienceModel experienceModel) {
		return ResponseEntity.ok(experienceService.addOrUpdate(experienceModel));
	}

	@PostMapping(path="addExperiences")
	public ResponseEntity<List<ExperienceModel>> addExperiences(@RequestBody List<ExperienceModel> experiencesModelList) {   
		return ResponseEntity.ok(experienceService.addExperiences(experiencesModelList));

	}

	@DeleteMapping(path = "deleteExperience/{id}")
	public String deleteExperience(@PathVariable("id") int id) {
		return experienceService.deleteExperience(id);
	}

	@GetMapping(path = "getExperiences/{userId}")
	public ResponseEntity<List<ExperienceModel>> getExpersByUserId(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(experienceService.getExperiencesByUserId(userId));
	}

	@GetMapping(path = "/getUserById/{userId}")
	@ResponseBody
	public ResponseEntity<UserModel> getUserById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@PutMapping(path="addJobPost")
	public ResponseEntity<JobPostModel> addOrUpdateJobPost(@RequestBody JobPostModel jobPostModel){
		return ResponseEntity.ok(jobPostService.addOrUpdate(jobPostModel));
	}

}
