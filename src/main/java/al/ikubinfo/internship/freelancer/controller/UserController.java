package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.model.UserModel;
import al.ikubinfo.internship.freelancer.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "user")
public class UserController {

	@Autowired
	private UserService userService;

	// TODO refactor this api: controllers should be clean and not have logic / exception managment inside
	@PostMapping(path = "registration")
	public ResponseEntity<RegistrationRequest> register(@RequestBody RegistrationRequest request) {
		return new ResponseEntity<>(userService.register(request),HttpStatus.OK);
	}

	@GetMapping(path = "registration/confirm")
	public String confirmEmail(@RequestParam("token") String token) {
		return userService.confirmToken(token);
	}

	@GetMapping(path = "/getUserById/{userId}")
	public ResponseEntity<UserModel> getUserById(@PathVariable("userId") Integer userId) {

		return ResponseEntity.ok(userService.getUserById(userId));
	}


}
