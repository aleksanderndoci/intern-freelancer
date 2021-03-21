package al.ikubinfo.internship.freelancer.service;

import al.ikubinfo.internship.freelancer.entity.ConfirmationToken;
import al.ikubinfo.internship.freelancer.entity.User;

import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.BuildEmail;
import al.ikubinfo.internship.freelancer.model.LoginRequest;
import al.ikubinfo.internship.freelancer.model.RegistrationRequest;
import al.ikubinfo.internship.freelancer.model.UserModel;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.registration.ConfirmationTokenService;
import al.ikubinfo.internship.freelancer.service.registration.EmailSender;
import al.ikubinfo.internship.freelancer.service.registration.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

	private final static String USER_NOT_FOUND = "User with email %s not found!";

	@Autowired
	private final UserRepository userRepository;

//	@Autowired
//	private final EmailValidator emailValidator;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	private EmailSender emailSender;

	private final Mapper<User, UserModel> userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}

	public RegistrationRequest register(RegistrationRequest request) {

//		boolean validEmail = emailValidator.test(request.getEmail());
//		if (!validEmail) {
//			throw new ResourceNotFoundException("EMAIL IS UNVALID!");
//
//		}
		try {
			String token = signUpUser(new User(request.getName(), request.getSurname(), request.getEmail(),
					request.getPassword(), request.getRole()));

			String fullName = request.getName().concat(" " + request.getSurname());
			String link = "http://localhost:8080/api/user/registration/confirm?token=" + token;
            emailSender.sendEmail(request.getEmail(), new BuildEmail().getBuildEmail(fullName, link));
			//emailSender.sendEmail(request.getEmail(), buildEmail(fullName, link));

			return request;

		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}

	public String signUpUser(User user) {
		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
		if (userExists) {
			throw new ResourceNotFoundException("Email is taken!");
		}
		String passEncoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(passEncoded);
		userRepository.save(user);

		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), user);

		confirmationTokenService.saveConfirmationToken(confirmationToken);
		return token;
	}

	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("token not found"));

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiresAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		confirmationTokenService.setConfirmedAt(token);
		userRepository.enableUser(confirmationToken.getUser().getEmail());

		return "confirmed";
	}

	public UserModel getUserById(Integer id) {
		try {
			User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
			return userMapper.toModel(user);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
		

	}
	

}