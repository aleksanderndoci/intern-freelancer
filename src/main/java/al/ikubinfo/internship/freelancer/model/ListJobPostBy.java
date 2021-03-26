package al.ikubinfo.internship.freelancer.model;

import lombok.Data;

@Data
public class ListJobPostBy {

	private String type;
	private String position;
	private int pageNr;
	private int pageSize;
	private String sortBy;	
}
