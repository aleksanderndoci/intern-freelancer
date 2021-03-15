package al.ikubinfo.internship.freelancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.model.ExperienceModel;
import al.ikubinfo.internship.freelancer.model.LoginRequest;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.service.ExperienceService;
import al.ikubinfo.internship.freelancer.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path="user")

public class UserController {

	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private ExperienceService experienceService;
	
	@PostMapping(path="registration")
	@ResponseBody
	public RegistrationRequest register(@RequestBody RegistrationRequest request) {
		try {
			userService.register(request);
			return request;
		}catch (IllegalStateException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@GetMapping(path="registration/confirm")
	public String confirmEmail(@RequestParam("token") String token)
	{
		return userService.confirmToken(token);
	}
	
	@PutMapping(path="login")
	public String login(@RequestBody LoginRequest request) {
		return userService.login(request);
	}
	
	@PutMapping(path="addExperience")
	public ExperienceModel addOrUpdate(@RequestBody ExperienceModel experienceModel) {
		experienceService.addOrUpdate(experienceModel);
		return experienceModel;
	}
	
	@PostMapping(path="addExperiences")
	public List<Experience> addExperiences(@RequestBody List<Experience> experiences) {
		return experienceService.addExperiences(experiences);
	}
	
	
	@DeleteMapping(path="deleteExperience/{id}")
	public String deleteExperience(@PathVariable int id) {
		return experienceService.deleteExperience(id);
	}
	
	
}
