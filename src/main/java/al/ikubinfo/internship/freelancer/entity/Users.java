package al.ikubinfo.internship.freelancer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "country")
	private String country;

	@Column(name = "activation_status")
	private String activationStatus;

	@Column(name = "role")
	@Enumerated(value = EnumType.STRING)
	@OneToOne
	private Role role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "skill_name"))
	private List<Skill> skills;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Education> education;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<JobPost> jobPosts;

	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="application",joinColumns = @JoinColumn(name="job_post_id"),
	inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<JobPost> jobPostsApplication;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	public List<JobPost> getJobPostsApplication() {
		return jobPostsApplication;
	}

	public void setJobPostsApplication(List<JobPost> jobPostsApplication) {
		this.jobPostsApplication = jobPostsApplication;
	}

}
