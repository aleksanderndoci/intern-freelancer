package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import lombok.Data;

@Data
public class JobPostModel {

	private Integer id;

	private String position;

	private String positionDescription;

	private Double salary;

	private String workingHour;

	private Date postDate;

	private Date updateDate;

	private String jobPostType;

	private UserModel userModel;
	

	public JobPostModel() {
		super();
	}

}
