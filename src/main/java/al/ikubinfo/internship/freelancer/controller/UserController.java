//package al.ikubinfo.internship.freelancer.controller;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import al.ikubinfo.internship.freelancer.entity.Users;
//import al.ikubinfo.internship.freelancer.model.UserModel;
//import al.ikubinfo.internship.freelancer.service.UserService;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//
//	@PostMapping("/register")
//	public ResponseEntity<UserModel> register( @RequestBody UserModel userModel) {
//		
//		userService.register(userModel);
//       return ResponseEntity.ok(userService.register(userModel));
//	}
//
//	@GetMapping("/getAll")
//	public List<Users> getAllUsers() {
//		List<Users> users = userService.getAllUsers();
//		return users;
//	}
//
//}
