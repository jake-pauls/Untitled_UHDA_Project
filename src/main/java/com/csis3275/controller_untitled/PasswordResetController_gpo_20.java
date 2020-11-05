package com.csis3275.controller_untitled;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;
import com.csis3275.model_untitled.PasswordReset_gpo_20;
import com.csis3275.model_untitled.User_untitled;

/**
 *  
 * @author Gregory Pohlod Student ID 300311820
 * @date Oct 25, 2020
 * PasswordResetController_gpo_20.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 *
 * EMAIL SERVICE FEATURE CLASSES AND METHODS:
 * utilized online walkthrough to plugin email functionaility found at the link below:
 * https://www.codebyamir.com/blog/forgot-password-feature-with-java-and-spring-boot
 * along with other resources to track down the required dependancies to use the JavaMailSender and SimpleMailMessage
 * utilized for the password reset email and possibly future Features or other feature emails in this project
 */
@Controller
public class PasswordResetController_gpo_20 {
	
	//connecting to the DAO and email services classes
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	@Autowired
	private EmailServiceImpl_untitled emailService;
	
	//mapping for when a user goest to the forgotpassword view
	@GetMapping("/forgotpassword")
	public ModelAndView showForgotPWForm(ModelAndView modelAndView) {
		PasswordReset_gpo_20 forgotpassword = new PasswordReset_gpo_20();
		modelAndView.addObject("resetmessage","");
		modelAndView.addObject("forgotpassword", forgotpassword);
		modelAndView.setViewName("forgotpassword");
		return modelAndView;
	}
	
	//mapping for when a user hits submit on the forgotpassword view in order to generate the reset password token and send the email with the reset link
	@PostMapping("/forgotpassword")
	public ModelAndView checkValidEmail(PasswordReset_gpo_20 forgotpassword, ModelAndView modelAndView, HttpServletRequest request) {
		User_untitled user = passwordResetDAO.checkUserEmailExists(forgotpassword.getEmail());
		//if a user with that email exists perform the below
		if(user !=null) {
			// Generate random 36-character string token for reset password 
			forgotpassword.setResetToken(UUID.randomUUID().toString());
			user.setResetToken(forgotpassword.getResetToken());
			passwordResetDAO.addResetTokenToUser(user);
			String resetPageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();
			
			// Email message with reset link using the email setup in the application.properties
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("uhda.untitled.csis3275@gmail.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" 
					+ resetPageUrl + "/reset?resetToken=" + user.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);
			modelAndView.addObject("resetSuccessMessage", "A Password reset link has been sent to "+ user.getEmail());
		} else { //else if a user with that email does not exist, let the user know it was spelt incorrectly or is not valid
			modelAndView.addObject("resetErrorMessage", "Email is not valid or is spelt incorrectly, try again");
		}
		modelAndView.addObject("forgotpassword", forgotpassword);
		modelAndView.setViewName("forgotpassword");
		return modelAndView;
	}
	
	//mapping for when the user goes to the reset view
	@GetMapping("/reset")
	//gets the resetToken from the URL
	public ModelAndView showResetPWForm(ModelAndView modelAndView, @RequestParam("resetToken") String resetToken) {
		PasswordReset_gpo_20 resetpassword = new PasswordReset_gpo_20();
		resetpassword.setResetToken(resetToken);
		//queries for user who matches the resetToken in the URL, if there is a User that has that toekn perform the below
		User_untitled user = passwordResetDAO.checkUserHasResetToken(resetpassword.getResetToken());
		if(user !=null) {
			modelAndView.addObject("resetpassword", resetpassword);
			modelAndView.addObject("resetmessage", "Enter New Password");
		} else { //if there is no user with the resetToken in the URL then tell the user it is an invalid link
			modelAndView.addObject("error", "Invalid Reset Link");
		}
		modelAndView.setViewName("reset");
		return modelAndView;
	}
	
	//when user submits their new password, the below method will will update the users password and reset the resetToken to null since it has been used
	@PostMapping("/reset")
	public RedirectView resetUsersPassword(@ModelAttribute("resetpassword") PasswordReset_gpo_20 resetpassword, ModelAndView modelAndView, RedirectAttributes redirectAttributes, @RequestParam("resetToken") String resetToken) {
		User_untitled user =  passwordResetDAO.checkUserHasResetToken(resetToken);
		user.setPassword(resetpassword.getPassword());
		passwordResetDAO.updatePasswordByResetToken(user);
		RedirectView redirectView = new RedirectView("/login", true);
		redirectAttributes.addFlashAttribute("successMessage", "Password successfully reset");
		return redirectView;
	}
	
}



