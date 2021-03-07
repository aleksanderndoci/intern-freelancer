package al.ikubinfo.internship.freelancer.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "test")
@Data
@EqualsAndHashCode(callSuper = true)
public class TestEntity extends BaseEntity {

    @Column(name = "value")
    private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
 
}
