package com.csis3275.dao_untitled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.csis3275.model_untitled.EmailService_untitled;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Oct 26, 2020
 * EmailServiceImpl_untitled.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 * 
 * EMAIL SERVICE FEATURE CLASSES AND METHODS:
 * utilized online walkthrough to plugin email functionaility found at the link below:
 * https://www.codebyamir.com/blog/forgot-password-feature-with-java-and-spring-boot
 * along with other resources to track down the required dependancies to use the JavaMailSender and SimpleMailMessage
 * utilized for the password reset email and possibly future Features or other feature emails in this project
 *
 */

//setting up the method for the javamailer to be sent by the application utilizing the account outlined in the application.properties
@Service("emailService")
public class EmailServiceImpl_untitled implements EmailService_untitled {
	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}
}
