package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import al.ikubinfo.internship.freelancer.service.JobPostService;

@RestController
@RequestMapping(path = "jobPost")
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;

}
