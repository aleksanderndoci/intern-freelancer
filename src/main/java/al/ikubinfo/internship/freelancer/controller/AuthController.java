package al.ikubinfo.internship.freelancer.controller;

import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.exception.ResourceNotFoundException;
import al.ikubinfo.internship.freelancer.security.model.LoginRequest;
import al.ikubinfo.internship.freelancer.security.model.LoginResponse;
import al.ikubinfo.internship.freelancer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@Slf4j
@RequestMapping("auth")
public class AuthController {
	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${server.host}")
	private String applicationHost;

	@Value("${server.port}")
	private Integer serverPort;

	@Autowired
	private UserService userService;

	@PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginDTO) {
		User user = userService.findByEmail(loginDTO.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("Email is written wrong."));
		if (userService.checkIfEnabled(user.getEmail())) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setBasicAuth(clientId, clientSecret);

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", grantType);
			map.add("username", loginDTO.getUsername());
			map.add("password", loginDTO.getPassword());

			HttpEntity<MultiValueMap<String, String>> loginRequest = new HttpEntity<>(map, headers);

			UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(applicationHost)
					.path("api/oauth/token").port(serverPort).build().encode();

			return ResponseEntity.ok(this.getJwt(uriComponents.toUri(), loginRequest));
		} else {
			throw new ResourceNotFoundException("You have not confirmed your email!");
		}
	}

	private LoginResponse getJwt(URI uri, HttpEntity<MultiValueMap<String, String>> loginRequest) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.postForObject(uri, loginRequest, LoginResponse.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AccessDeniedException(e.getMessage());
		}
	}

}
