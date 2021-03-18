package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import al.ikubinfo.internship.freelancer.entity.User;
import lombok.Data;

@Data
public class JobPostModel {

	private Integer id;

	private String position;

	private String positionDescription;

	private double salary;

	private String workingHour;

	private Date jobPostDate;

	private String jobPostType;

	private User user;
}
