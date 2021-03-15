package al.ikubinfo.internship.freelancer.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode 
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users",
uniqueConstraints = {
		@UniqueConstraint(name="user_email_unique",columnNames = "email")
		
}
)
public class Users implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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

	@Enumerated(value = EnumType.STRING)
	@Column(name = "role")
	private Role role;
	
	
	@Column(name="locked")
	private Boolean locked =false;
	
	@Column(name="enabled")
	private Boolean enabled =false;

	@ManyToMany
	@JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_name"))
	private List<Skill> skills;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Education> education;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<JobPost> jobPosts;

	@ManyToMany
	@JoinTable(name = "application", joinColumns = @JoinColumn(name = "job_post_id"),
			 inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<JobPost> jobPostsApplication;
	
	//inverse references
	@OneToMany(mappedBy = "user")
	List<Application> applicationStatus;

	

	public Users(String name, String surname, String email, String passw, Role role
//			, Date birthday, String country,
//			String activationStatus
			) 
			{
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = passw;
		this.role=role;
//		this.birthday = birthday;
//		this.country = country;
//		this.activationStatus=activationStatus;
//	
	}
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());

		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}




	public Users(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}



}
