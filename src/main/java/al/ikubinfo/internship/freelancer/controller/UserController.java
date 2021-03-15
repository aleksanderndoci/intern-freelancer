package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import al.ikubinfo.internship.freelancer.model.LoginRequest;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="user")
public class UserController {

	
	@Autowired
	private UserService userService ;
	
	@PostMapping(path="registration")
	public String register(@RequestBody RegistrationRequest request) {
		try {
			return userService.register(request);
		}catch (IllegalStateException e){
			System.out.println(e.getMessage());
		}
		return "Sorry, email is taken!";
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
}
