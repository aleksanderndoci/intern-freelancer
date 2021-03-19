package al.ikubinfo.internship.freelancer.entity;

public enum Role {

	ADMIN("ADMIN"), 
	EMPLOYER("EMPLOYER"), 
	FREELANCER("FREELANCE");

	private String role;

	Role(String role) {
		this.role = role;
	}

	public String getMessage() {
		return role;
	}

}
