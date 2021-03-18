package al.ikubinfo.internship.freelancer.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
public class Application {
	
	@EmbeddedId
	private ApplicationKey id; //mark PK
	
	@ManyToOne
	@MapsId("jobPostId")
	@JoinColumn(name="job_post_id")
	private JobPost jobPost;
	//fk
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
	
	private String applicationStatus;
	

}
