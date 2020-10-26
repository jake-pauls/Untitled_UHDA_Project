package com.csis3275.controller_untitled;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jacob Pauls
 * @date Oct 26, 2020
 * Untitled_UHDA_Project
 */

@Controller
public class UserHomePageController_untitled {

	@RequestMapping(value = "/userHomePage", method = RequestMethod.GET)	
	public ModelAndView userHomePage(HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("userHomePage_untitled");
		return modelAndView;
	}
	
}
