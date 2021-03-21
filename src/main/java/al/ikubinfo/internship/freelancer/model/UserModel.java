package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import java.util.List;
import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.entity.JobPost;
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
