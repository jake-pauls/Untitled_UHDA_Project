package com.csis3275.controller_untitled;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Oct 20, 2020
 * IndexController_untitled.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Controller
public class IndexController_untitled {

	private final String LANDING_PAGE_MESSAGE = "Hello from Untitled! Click the link below to login.";
	
	/**
	 * Request mapped to landing point for SpringBoot application
	 * @param model ModelMap object containing data to be passed to the landing page view
	 * @return String containing the name for the destination view
	 */
	@RequestMapping("/")
	public String index(ModelMap model) {
		model.put("helloMessage", LANDING_PAGE_MESSAGE);
		return "LandingPage";
	}
	

}
