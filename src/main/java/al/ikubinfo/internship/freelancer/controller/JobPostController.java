package al.ikubinfo.internship.freelancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(path = "getByUserId/{userId}")
	public ResponseEntity<List<JobPostModel>> getJobPostsByUserId(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(jobPostService.listJobPostsByUserId(userId));
	}

	@DeleteMapping(path = "deleteExperience/{id}")
	public ResponseEntity<HttpStatus> deleteJobPost(@PathVariable("id") Integer id) {
		jobPostService.deleteJobPost(id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="listJobDemandByPosition/{type}/{position}")
	public ResponseEntity<List<JobPostModel>> listJobDemandByPosition(@PathVariable("type") String type, @PathVariable("position") String position){
		return ResponseEntity.ok(jobPostService.findByPosition(type,position));
	}
	
}
