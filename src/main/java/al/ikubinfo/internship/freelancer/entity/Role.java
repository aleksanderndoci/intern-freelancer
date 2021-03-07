package al.ikubinfo.internship.freelancer.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public enum Role {

	ADMIN("ADMIN"), EMPLOYER("EMPLOYER"), FREELANCER("FREELANCE");

	private String message;

	Role(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
