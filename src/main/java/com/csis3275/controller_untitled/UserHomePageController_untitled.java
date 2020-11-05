package com.csis3275.controller_untitled;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.utility_untitled.UserAuthenticationUtilities_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Oct 26, 2020
 * UserHomePageController_untitled.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Controller
public class UserHomePageController_untitled {
	
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	/**
	 * GET request mapped to the user's home page view
	 * @param session Currently observed HTTP session
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return ModelAndView object containing the view name for the user home page
	 */
	@RequestMapping(value = "/UserHomePage", method = RequestMethod.GET)	
	public ModelAndView userHomePage(HttpSession session, ModelAndView modelAndView, Principal principal) {
		modelAndView.addObject("loggedInUser", authenticatedUser.getLoggedInUserContext(principal));
		modelAndView.setViewName("UserHomePage");
		return modelAndView;
	}
	
}
