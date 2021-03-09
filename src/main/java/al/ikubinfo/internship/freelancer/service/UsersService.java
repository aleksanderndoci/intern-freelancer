package al.ikubinfo.internship.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {

	private final static String USER_NOT_FOUND = "User with email %s not found!";
	@Autowired
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() ->
				new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}
	
	public String register(RegistrationRequest request) {
		return "works";
	}

}
