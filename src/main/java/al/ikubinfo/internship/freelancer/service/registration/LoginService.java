package al.ikubinfo.internship.freelancer.service.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import al.ikubinfo.internship.freelancer.entity.Users;
import al.ikubinfo.internship.freelancer.model.LoginRequest;
import al.ikubinfo.internship.freelancer.repository.login.LoginRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public String login(LoginRequest loginRequest) {
		String passEncoded = bCryptPasswordEncoder.encode(loginRequest.getPassword());
		return validUser(new Users(loginRequest.getEmail(), passEncoded));
		
	}
	
	public String validUser(Users user) {
		boolean userExists = loginRepository.validUser(
				user.getEmail(),user.getPassword());
		
		if(userExists) {
			loginRepository.lockUser(user.getEmail());
		    return "User is logged in.";
		}
		return "Wrong username or password";
		
	}
}
