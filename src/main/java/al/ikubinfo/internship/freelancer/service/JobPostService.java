package al.ikubinfo.internship.freelancer.service;

import java.util.List;
import org.springframework.http.HttpStatus;

import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.model.ListJobPostBy;

public interface JobPostService {

	JobPostModel addJobPost(JobPostModel jobPostModel);
	JobPostModel updateJobPost(JobPostModel jobPostModel);
	List<JobPostModel> listJobPostsByUserId(int userId);
	HttpStatus deleteJobPost(Integer id);
	List<JobPostModel> findByPosition(ListJobPostBy listBy);
	
}
