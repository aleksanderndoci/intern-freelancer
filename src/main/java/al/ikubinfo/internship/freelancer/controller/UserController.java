package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.model.RegistrationResponse;
import al.ikubinfo.internship.freelancer.model.UserModel;
import al.ikubinfo.internship.freelancer.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping(path = "user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "registration")
	public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
		return new ResponseEntity<>(userService.register(request),HttpStatus.OK);
	}

	@GetMapping(path = "registration/confirm")
	public String confirmEmail(@RequestParam("token") String token) {
		return userService.confirmToken(token);
	}

	
	@GetMapping(path = "getUserById/{userId}")
	public ResponseEntity<UserModel> getUserById(@PathVariable("userId") Integer userId) {

		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@PutMapping("changePassword/{id}")
	@PreAuthorize("hasAnyAuthority('FREELANCER','EMPLOYER','ADMIN')")
	public String changePassword(@PathVariable Integer id, @RequestParam(required = false) String oldPassword,
			@Valid @NotNull @Size(min = 3) @RequestParam String newPassword,@Valid @NotNull @Size(min = 3) @RequestParam String newPasswordRetyped) {
		userService.changePassword(id, oldPassword, newPassword,newPasswordRetyped);
		return "changed";
	}

}
