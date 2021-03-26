package al.ikubinfo.internship.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	@PreAuthorize("hasAnyAuthority('FREELANCER','EMPLOYER')")
	public ResponseEntity<ApplicationModel> apply(@RequestBody ApplicationKey key) {
		return new ResponseEntity<>(applicationService.apply(key), HttpStatus.CREATED);
	}

	@PostMapping(path = "getApplicationByIds")
	@PreAuthorize("hasAnyAuthority('FREELANCER','EMPLOYER')")
	public ResponseEntity<ApplicationModel> getApplicationByIds(@RequestBody ApplicationKey key) {
		return new ResponseEntity<>(applicationService.getApplicationByPK(key), HttpStatus.OK);
	}

	@PostMapping(path = "accept")
	@PreAuthorize("hasAnyAuthority('FREELANCER','EMPLOYER')")
	public ResponseEntity<ApplicationModel> accept(@RequestBody ApplicationKey applicationKey) {
		return new ResponseEntity<>(applicationService.accept(applicationKey), HttpStatus.OK);
	}

	@PostMapping(path = "refuse")
	@PreAuthorize("hasAnyAuthority('FREELANCER','EMPLOYER')")
	public ResponseEntity<ApplicationModel> refuse(@RequestBody ApplicationKey applicationKey) {
		return new ResponseEntity<>(applicationService.refuse(applicationKey), HttpStatus.OK);
	}
}
