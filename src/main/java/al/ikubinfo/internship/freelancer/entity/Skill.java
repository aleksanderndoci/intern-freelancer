package al.ikubinfo.internship.freelancer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "skill")
public class Skill {

	@Column(name="skill_name")
	private String skillName;

	@ManyToMany(mappedBy="skills")
	private List<Users> users;
	
	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
	
}
