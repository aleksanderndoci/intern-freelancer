package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import al.ikubinfo.internship.freelancer.entity.ApplicationKey;
import al.ikubinfo.internship.freelancer.model.ApplicationModel;
import al.ikubinfo.internship.freelancer.service.ApplicationService;

@RestController
@RequestMapping(path = "application")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@PostMapping(path = "apply")
	public ResponseEntity<ApplicationModel> apply(@RequestBody ApplicationKey key) {
		return new ResponseEntity<>(applicationService.apply(key), HttpStatus.CREATED);
	}

	@GetMapping(path="getApplicationByIds")
		public  ResponseEntity<ApplicationModel> getApplicationByIds(@RequestBody ApplicationKey key) {
			return new ResponseEntity<>(applicationService.getApplicationByPK(key),HttpStatus.OK);
		}
}
