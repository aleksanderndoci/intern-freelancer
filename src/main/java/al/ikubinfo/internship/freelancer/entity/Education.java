package al.ikubinfo.internship.freelancer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "education")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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
	@JoinColumn(name = "user_id",foreignKey = @ForeignKey(name="user_id_FK"))
	private Users user;

	

}
