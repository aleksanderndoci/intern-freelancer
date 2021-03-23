package al.ikubinfo.internship.freelancer.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import al.ikubinfo.internship.freelancer.entity.Application;
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

	@JsonCreator
	public ApplicationModel(@JsonProperty("applicationStatus")String applicationStatus,@JsonProperty("applicationDate") Date applicationDate,
			@JsonProperty("acceptDate")Date acceptDate,
			@JsonProperty("refuseDate")Date refuseDate) {
		super();
		this.applicationStatus = applicationStatus;
		this.applicationDate = applicationDate;
		this.acceptDate = acceptDate;
		this.refuseDate = refuseDate;
	}

}
