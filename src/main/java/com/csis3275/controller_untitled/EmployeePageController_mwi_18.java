package com.csis3275.controller_untitled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;

@Controller
public class EmployeePageController_mwi_18 {
	
	
	
	@GetMapping("/employeeHomePage")
	public String openPage(ModelAndView view) {
		
		view.setViewName("employeeHomePage");
		
		return view.getViewName();
	}
}
