package al.ikubinfo.internship.freelancer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "experience")
public class Experience extends BaseEntity {

	@Column(name = "name_of_company")
	private String nameOfCompany;

	@Column(name = "position")
	private String position;

	@Column(name = "position_description")
	private String positionDescription;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public String getNameOfCompany() {
		return nameOfCompany;
	}

	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionDescription() {
		return positionDescription;
	}

	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
