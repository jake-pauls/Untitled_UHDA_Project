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
import com.csis3275.model_untitled.Login_mwi_18;

import com.csis3275.model_untitled.User_untitled;

@Controller
public class Login_RegisterController_mwi_18 {
	
	@Autowired
	Login_RegisterDAO_mwi_18 dao;
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		Login_mwi_18 login = new Login_mwi_18();
		model.addAttribute("test","");
		model.addAttribute("login",login);
		
		return "login";
	}
	
	@PostMapping("/login")
	public String attemptLogin(Login_mwi_18 login,Model model) {
		
		User_untitled user = dao.checkCredentials(login.getUsername(), login.getPassword());
		if(user != null) {
			model.addAttribute("test","Login succesful");
		}else {
			model.addAttribute("test","Username or Password incorrect");
		}
		
		model.addAttribute("login",login);
		
		return "login";
	}
	
	@ModelAttribute("sq")
	public List<String> getSq(Model model){
		List<String> list = new ArrayList<String>();
		
		list.add("What is the name of your first pet?");
		list.add("What is the name of your primary school?");
		list.add("What is your favorite movie?");
		
		
		return list;
	}
	
	@GetMapping("/register")
	public String showRegistrationPage(Model model) {
		
		User_untitled user = new User_untitled();
		model.addAttribute("user", user);
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(User_untitled user,Model model) {
		Login_mwi_18 login = new Login_mwi_18();
		model.addAttribute("login",login);
		user.setResetToken(null);
		
		if(dao.checkEmail(user.getEmail())) {
			User_untitled newUser = new User_untitled();
			model.addAttribute("user", newUser);
			model.addAttribute("error","Sorry that email is already being used");
			return "register";
		}else {
			if (dao.createUser(user) == 1) {
				model.addAttribute("test","user created");
			}else{
				model.addAttribute("user", user);
				model.addAttribute("error","Sorry that username is already being used");
				return "register";
			}
			
			return "login";
		}
	}
		
}
