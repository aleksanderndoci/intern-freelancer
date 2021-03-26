package al.ikubinfo.internship.freelancer.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="application")
public class Application {

	@EmbeddedId
	private ApplicationKey id;

	@Column(name = "application_status")
	private String applicationStatus;

	@Column(name = "application_date")
	private Date applicationDate;

	@Column(name = "accept_date")
	private Date acceptDate;

	@Column(name = "refuse_date")
	private Date refuseDate;

}
