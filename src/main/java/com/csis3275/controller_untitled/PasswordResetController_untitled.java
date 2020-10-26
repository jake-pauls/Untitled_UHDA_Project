package com.csis3275.controller_untitled;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.PasswordResetServiceImpl_untitled;
import com.csis3275.model_untitled.PasswordResetInfo_untitled;


@Controller
public class PasswordResetController_untitled {

	@Autowired
	private PasswordResetServiceImpl_untitled passwordResetService;

	@Autowired
	private EmailServiceImpl_untitled emailService;
	
	// We need a blank student for the add form
	@ModelAttribute("user")
	public  PasswordResetInfo_untitled setupAddForm() {
		return new PasswordResetInfo_untitled();
	}


	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public ModelAndView displayForgotPasswordPage(ModelAndView modelAndView) {
		return new ModelAndView("forgot-password");
    }
    
    // Process form submission from forgotPassword page
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

		// Lookup user in database by e-mail
		PasswordResetInfo_untitled resetUser = passwordResetService.getUserByEmail(userEmail);

		if (resetUser.getEmail() != null) {
			modelAndView.addObject("existsmessage", "the user exists");
		}
		else {
			modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
			
		}
		/*
		if (!optional.isPresent()) {
			modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
		} else {
			
			// Generate random 36-character string token for reset password 
			PasswordResetInfo_untitled user = optional.get();
			user.setResetToken(UUID.randomUUID().toString());

			// Save token to database
			passwordResetService.saveResetToken(user.getResetToken(), user.getEmail());;

			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ "/reset?token=" + user.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);

			// Add success message to view
			modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
		}
*/
		modelAndView.setViewName("forgot-password");
		return modelAndView;

	}

	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("resetToken") String resetToken) {
		
		Optional<PasswordResetInfo_untitled> user = passwordResetService.getUserByResetToken(resetToken);

		if (user.isPresent()) { // Token found in DB
			modelAndView.addObject("resetToken", resetToken);
		} else { // Token not found in DB
			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
		}

		modelAndView.setViewName("resetPassword");
		return modelAndView;
	}

	// Process reset password form
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

		// Find the user associated with the reset token
		Optional<PasswordResetInfo_untitled> user = passwordResetService.getUserByResetToken(requestParams.get("resetToken"));

		// This should always be non-null but we check just in case
		if (user.isPresent()) {
			
			PasswordResetInfo_untitled resetUser = user.get(); 
            
			// Set new password    
            resetUser.setPassword(requestParams.get("password"));
            
			// Set the reset token to null so it cannot be used again
			resetUser.setResetToken(null);

			// Save user
			passwordResetService.savePassword(resetUser.getPassword(), resetUser.getResetToken());

			// In order to set a model attribute on a redirect, we must use
			// RedirectAttributes
			redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

			modelAndView.setViewName("redirect:login");
			return modelAndView;
			
		} else {
			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
			modelAndView.setViewName("resetPassword");	
		}
		
		return modelAndView;
   }
   
    // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:login");
	}
}

