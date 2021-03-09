package al.ikubinfo.internship.freelancer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 

public enum Role{
    
	ADMIN("ADMIN"), EMPLOYER("EMPLOYER"), FREELANCER("FREELANCE");
	
    
	private String role;

	
	Role(String role) {
		this.role = role;
	}

	public String getMessage() {
		return role;
	}

}
