package al.ikubinfo.internship.freelancer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor

public class ApplicationKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="job_post_id")
	Integer jobPostId;
	
	@Column(name="user_id")
	Integer userId;
	
}
