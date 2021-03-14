package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import al.ikubinfo.internship.freelancer.model.LoginRequest;
import al.ikubinfo.internship.freelancer.service.registration.LoginService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="user/login")
@AllArgsConstructor
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PutMapping
	public String login(@RequestBody LoginRequest request) {
		return loginService.login(request);
	}
}
