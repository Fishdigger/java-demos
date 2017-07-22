package edu.mga.itec4269.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
	private JavaMailSender mailer;
	
	@Autowired
	public EmailService(JavaMailSender mailer){
		this.mailer = mailer;
	}
	
	@Async
	public void sendEmail(SimpleMailMessage email){
		mailer.send(email);
	}
}
