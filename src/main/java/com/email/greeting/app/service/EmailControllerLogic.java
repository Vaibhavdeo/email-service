package com.email.greeting.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.email.greeting.beans.GreetingDetails;

@Component
public class EmailControllerLogic {

	private RestTemplate restTemplate;
	@Autowired
	private EmailSender emailSender;

	/**
	 * This method is for to get the details by calling the service
	 * getAllDetails
	 * 
	 * @throws MessagingException
	 */
	@Scheduled(cron = "0 0 10 * * *")
	public void getAllDetails() throws MessagingException {
		
		restTemplate = new RestTemplate();
		ResponseEntity<List<GreetingDetails>> responseDetails = restTemplate.exchange(
				"https://heroku-greeting-sender.herokuapp.com/getAllEvents", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<GreetingDetails>>() {
				});
		List<GreetingDetails> greetingDetails = responseDetails.getBody();
		greetingDetails.forEach(list -> {
				try {
					emailSender.sendEmail(list);
				} catch (Exception e) {					
					e.printStackTrace();
				}
		});

	}

}
