package com.email.greeting.app.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.email.greeting.beans.GreetingDetails;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender sender;

	public void sendEmail(GreetingDetails details) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(details.getMailId());
		//helper.setText(details.getSubject());
		helper.setSubject("Happy"+" " +details.getSubject() + " " + details.getName());
		sender.send(message);

	}

}
