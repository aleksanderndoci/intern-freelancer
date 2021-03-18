package al.ikubinfo.internship.freelancer.model;

import java.util.Date;

import al.ikubinfo.internship.freelancer.entity.User;
import lombok.Data;

@Data
public class ExperienceModel {

	private Integer id;

	private String nameOfCompany;

	private String position;

	private String positionDescription;

	private Date startDate;

	private Date endDate;
	
	private User user;
	
	
	
}