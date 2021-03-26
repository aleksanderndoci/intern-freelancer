package al.ikubinfo.internship.freelancer.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "job_post")
public class JobPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "position")
	private String position;

	@Column(name = "position_description")
	private String positionDescription;

	@Column(name = "salary")
	private Double salary;

	@Column(name = "working_hour")
	private String workingHour;

	@Column(name = "post_date")
	private Date postDate ;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "job_post_type")
	private String jobPostType;

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_FK"))
	private User user;

}
