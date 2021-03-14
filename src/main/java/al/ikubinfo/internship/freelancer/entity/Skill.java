package al.ikubinfo.internship.freelancer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity 
@Table(name = "skill")
public class Skill  {

	@Id
	@Column(name="skill_name")
	private String skillName;

	@ManyToMany(mappedBy="skills")
	private List<Users> users;
	
	
	
}
