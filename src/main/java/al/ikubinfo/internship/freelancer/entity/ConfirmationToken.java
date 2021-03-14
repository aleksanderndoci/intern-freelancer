package al.ikubinfo.internship.freelancer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "token")
	private String token;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "expired_at")
	private LocalDateTime expiresAt;

	@Column(name = "confirmed_at")
	private LocalDateTime confirmedAt;

	@ManyToOne
	@JoinColumn(nullable = false, name = "user_id")
	private Users user;

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, 
			Users user) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiredAt;
		
		this.user = user;
	}

}
