package com.email.greeting.app.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class EmailServiceController {
	
	@RequestMapping(value = "/checkmailService", method = RequestMethod.GET)
	public String IsEmailServiceStarted() {
		
		return "Email Service is Up and running";
	}
		
}
