package al.ikubinfo.internship.freelancer.entity;

import java.util.Date; 
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_post")
public class JobPost {

	@Column(name = "position")
	private String position;

	@Lob
	@Column(name = "position_description")
	private String positionDescription;

	@Column(name = "salary")
	private double salary;

	@Column(name = "working_hour")
	private String workingHour;

	@Column(name = "job_post_date")
	private Date jobPostDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@ManyToMany(mappedBy = "jobPostsApplication")
	private List<Users> usersApplication;

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getWorkingHour() {
		return workingHour;
	}

	public void setWorkingHour(String workingHour) {
		this.workingHour = workingHour;
	}

	public Date getJobPostDate() {
		return jobPostDate;
	}

	public void setJobPostDate(Date jobPostDate) {
		this.jobPostDate = jobPostDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Users> getUsersApplication() {
		return usersApplication;
	}

	public void setUsersApplication(List<Users> usersApplication) {
		this.usersApplication = usersApplication;
	}
	
	

}
