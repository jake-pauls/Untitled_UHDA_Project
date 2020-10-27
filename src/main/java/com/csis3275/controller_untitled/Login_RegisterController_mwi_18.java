package com.csis3275.controller_untitled;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView attemptLogin(Login_mwi_18 login,ModelAndView modelAndView) {
		
		User_untitled user = dao.checkCredentials(login.getUsername(), login.getPassword());
		if(user != null) {
			modelAndView.addObject("test","Login succesful");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("userHomePage_untitled");
		}else {
			modelAndView.addObject("test","Username or Password incorrect");
			modelAndView.setViewName("login");
		}
		
		modelAndView.addObject("login",login);
		
		return modelAndView;
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
		
		if (dao.createUser(user) == 1) {
			model.addAttribute("test","user created");
		}else{
			model.addAttribute("user", user);
			model.addAttribute("error","That username has already been taken");
			return "register";
		}
		
		return "login";
	}
}
