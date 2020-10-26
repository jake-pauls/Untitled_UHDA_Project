package com.csis3275.controller_untitled;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jacob Pauls
 * @date Oct 20, 2020
 * Untitled_UHDA_Project
 */

@Controller
public class IndexController_Untitled {

	@RequestMapping("/")
	public String index(ModelMap model) {
		model.put("helloMessage", "Hello from Untitled!");
		return "landing_page_untitled";
	}
	
}
