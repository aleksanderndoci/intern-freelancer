package al.ikubinfo.internship.freelancer.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Table(name="application")
public class Application {

	@EmbeddedId
	private ApplicationKey id; // mark PK
//
	@ManyToOne
	@MapsId("jobPostId") 
	@JoinColumn(name = "job_post_id")
	private JobPost jobPost;
	// fk
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "application_status")
	private String applicationStatus;

	@Column(name = "application_date")
	private Date applicationDate;

	@Column(name = "accept_date")
	private Date acceptDate;

	@Column(name = "refuse_date")
	private Date refuseDate;

}
