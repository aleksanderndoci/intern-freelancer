package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import al.ikubinfo.internship.freelancer.model.JobPostModel;
import al.ikubinfo.internship.freelancer.service.JobPostService;

@RestController
@RequestMapping(path = "jobPost")
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;

	@PostMapping(path = "addJobPost")
	public ResponseEntity<JobPostModel> addJobPost(@RequestBody JobPostModel jobPostModel) {
		return new ResponseEntity<>(jobPostService.addJobPost(jobPostModel), HttpStatus.CREATED);
	}

	@PutMapping(path = "updateJobPost")
	public ResponseEntity<JobPostModel> updateExperience(@RequestBody JobPostModel jobPostModel) {
		return new ResponseEntity<>(jobPostService.updateJobPost(jobPostModel), HttpStatus.OK);
	}
}
