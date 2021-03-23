package al.ikubinfo.internship.freelancer.model;

import java.util.Date;

import lombok.Data;

@Data
public class RegistrationResponse {

	private String email;
	private String token;
	private String message;
	private Date date;
}
