package al.ikubinfo.internship.freelancer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="education")
public class Education extends BaseEntity {

	@Column(name = "name_of_institution")
	private String nameOfInstitution;

	@Column(name = "level")
	private String level;

	@Column(name = "field_of_study")
	private String fieldOfStudy;

	@Column(name = "extra_info")
	private String extraInfo;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public String getNameOfInstitution() {
		return nameOfInstitution;
	}

	public void setNameOfInstitution(String nameOfInstitution) {
		this.nameOfInstitution = nameOfInstitution;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
