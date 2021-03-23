package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import al.ikubinfo.internship.freelancer.entity.Application;
import lombok.Data;

@Data
public class JobPostModel {

	private Integer id;

	private String position;

	private String positionDescription;

	private double salary;

	private String workingHour;

	private Date postDate;

	private Date updateDate;

	private String jobPostType;

	private UserModel userModel;
	

	public JobPostModel() {
		super();
	}

	@JsonCreator
	public JobPostModel(@JsonProperty("position") String position,
			@JsonProperty("positionDescription") String positionDescription, @JsonProperty("salary") double salary,
			@JsonProperty("workingHour") String workingHour, @JsonProperty("userModel") UserModel userModel) {
		super();
		this.position = position;
		this.positionDescription = positionDescription;
		this.salary = salary;
		this.workingHour = workingHour;
		this.userModel = userModel;
	}

}
