package al.ikubinfo.internship.freelancer.model;

import al.ikubinfo.internship.freelancer.entity.Role;
import lombok.Data;

@Data
public class UserModel {
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private Role role;
}
