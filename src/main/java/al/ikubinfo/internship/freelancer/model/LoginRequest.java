package al.ikubinfo.internship.freelancer.model;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String email;
	private String password;
	private boolean locked;
}
