package al.ikubinfo.internship.freelancer.model;

import al.ikubinfo.internship.freelancer.entity.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
	private final String name;
	private final String surname;
	private final String email;
	private final String password;
	private final Role role;
	
}
