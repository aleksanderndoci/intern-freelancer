package al.ikubinfo.internship.freelancer.model;

import lombok.Data;

@Data
public class TestModel {
    private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
}
