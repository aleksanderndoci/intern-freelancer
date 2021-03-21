package al.ikubinfo.internship.freelancer.service;

import al.ikubinfo.internship.freelancer.model.JobPostModel;

public interface JobPostService {

	JobPostModel addJobPost(JobPostModel jobPostModel);
	
	JobPostModel updateJobPost(JobPostModel jobPostModel);
}
