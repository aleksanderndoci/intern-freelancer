package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import al.ikubinfo.internship.freelancer.entity.ApplicationKey;
import lombok.Data;

@Data
public class ApplicationModel {

	private ApplicationKey appKeyId;

	private String applicationStatus;

	private Date applicationDate;

	private Date acceptDate;

	private Date refuseDate;


	public ApplicationModel() {
		super();
	}

}
