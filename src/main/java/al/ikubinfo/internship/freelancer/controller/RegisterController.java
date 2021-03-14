package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.service.registration.RegistrationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="user/registration")
@AllArgsConstructor
public class RegisterController {

	
	@Autowired
	private RegistrationService registrationService ;
	
	@PostMapping()
	public String register(@RequestBody RegistrationRequest request) {
		try {
			return registrationService.register(request);
		}catch (IllegalStateException e){
			System.out.println(e.getMessage());
		}
		return "Sorry, email is taken!";
	}
	
	@GetMapping(path="confirm")
	public String confirmEmail(@RequestParam("token") String token)
	{
		return registrationService.confirmToken(token);
	}
	
}
