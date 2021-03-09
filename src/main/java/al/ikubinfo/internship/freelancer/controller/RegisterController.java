package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.service.UsersService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="user/registration")
@AllArgsConstructor
public class RegisterController {

	
	@Autowired
	private UsersService userService;
	
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return userService.register(request);
	}
	
	
}
