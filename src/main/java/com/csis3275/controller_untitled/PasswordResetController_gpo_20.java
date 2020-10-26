package com.csis3275.controller_untitled;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;
import com.csis3275.model_untitled.PasswordReset_gpo_20;
import com.csis3275.model_untitled.User_untitled;

@Controller
public class PasswordResetController_gpo_20 {
	@Autowired
	
	PasswordResetDAO_gpo_20 passwordResetDAO;
	@Autowired
	private EmailServiceImpl_untitled emailService;
	
	@GetMapping("/forgotpassword")
	public String showForgotPWForm(Model model) {
		PasswordReset_gpo_20 forgotpassword = new PasswordReset_gpo_20();
		model.addAttribute("resetmessage","");
		model.addAttribute("forgotpassword", forgotpassword);
		return "forgotpassword";
	}
	
	@PostMapping("/forgotpassword")
	public String checkValidEmail(PasswordReset_gpo_20 forgotpassword, Model model, HttpServletRequest request) {
		User_untitled user = passwordResetDAO.checkUserEmailExists(forgotpassword.getEmail());
		if(user !=null) {
			// Generate random 36-character string token for reset password 
			forgotpassword.setResetToken(UUID.randomUUID().toString());
			user.setResetToken(forgotpassword.getResetToken());
			passwordResetDAO.addResetTokenToUser(user);
			String resetPageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("uhda.untitled.csis3275@gmail.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" 
					+ resetPageUrl + "/reset?resetToken=" + user.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);
			model.addAttribute("resetmessage", "A Password reset link has been sent to "+ user.getEmail());
		} else {
			model.addAttribute("resetmessage", "Email is not valid or is spelt incorrectly, try again");
		}
		model.addAttribute("forgotpassword", forgotpassword);
		return "forgotpassword";
	}
	
	@GetMapping("/reset")
	public String showResetPWForm(Model model, @RequestParam("resetToken") String resetToken) {
		PasswordReset_gpo_20 resetpassword = new PasswordReset_gpo_20();
		resetpassword.setResetToken(resetToken);
		User_untitled user = passwordResetDAO.checkUserHasResetToken(resetpassword.getResetToken());
		if(user !=null) {
			model.addAttribute("resetpassword", resetpassword);
			model.addAttribute("resetmessage", "Enter New Password");
		} else {
			model.addAttribute("error", "Invalid Reset Link");
		}
		return "reset";
	}
	
	@PostMapping("/reset")
	public String resetUsersPassword(@ModelAttribute("resetpassword") PasswordReset_gpo_20 resetpassword, Model model, @RequestParam("resetToken") String resetToken) {
		User_untitled user =  passwordResetDAO.checkUserHasResetToken(resetToken);
		user.setPassword(resetpassword.getPassword());
		passwordResetDAO.updatePasswordByResetToken(user);
		model.addAttribute("test","Password successfully Reset");
		return "redirect:/login";
	}
	
}



