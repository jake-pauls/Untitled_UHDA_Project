package com.csis3275.controller_untitled;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.dao_untitled.Login_RegisterDAO_mwi_18;
import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;
import com.csis3275.model_untitled.Login_mwi_18;
import com.csis3275.model_untitled.PasswordReset_gpo_20;
import com.csis3275.model_untitled.User_untitled;

@Controller
public class PasswordResetController_gpo_20 {
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	
	@GetMapping("/forgotpassword")
	public String showForgotPWForm(Model model) {
		PasswordReset_gpo_20 forgotpassword = new PasswordReset_gpo_20();
		model.addAttribute("resetmessage","");
		model.addAttribute("forgotpassword", forgotpassword);
		return "forgotpassword";
	}
	
	@PostMapping("/forgotpassword")
	public String checkValidEmail(PasswordReset_gpo_20 forgotpassword, Model model) {
		User_untitled user = passwordResetDAO.checkUserEmailExists(forgotpassword.getEmail());
		if(user !=null) {
			model.addAttribute("resetmessage", "Valid Email");
		} else {
			model.addAttribute("resetmessage", "Email is not valid or is spelt incorrectly, try again");
		}
		model.addAttribute("forgotpassword", forgotpassword);
		return "forgotpassword";
	}
}
