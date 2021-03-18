package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import java.util.List;
import al.ikubinfo.internship.freelancer.entity.Experience;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.Role;
import lombok.Data;

@Data
public class UserModel {

	private String name;
	private String surname;
	private String fullName;
	private String email;
	private String password;
	private String country;
	private Date birthday;
	private String activationStatus;
	private Role role;
	private List<Experience> experiences;
	private List<JobPost> jobPosts;
	private List<JobPost> jobPostApplications;

	public UserModel() {
		super();
	}

}
