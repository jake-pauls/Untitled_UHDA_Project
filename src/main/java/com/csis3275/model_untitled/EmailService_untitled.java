package com.csis3275.model_untitled;

import org.springframework.mail.SimpleMailMessage;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Oct 26, 2020
 * EmailService_untitled.java
 * com.csis3275.model_untitled
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

//interface used for sending an email though the app using the java mailers
public interface EmailService_untitled {
	public void sendEmail(SimpleMailMessage email);
}
