package com.csis3275.controller_untitled;

import java.io.Console;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.utility_untitled.UserAuthenticationUtilities_untitled;


@Controller
public class EmployeePageController_mwi_18 {
	
	@Autowired
	TicketDisplayDAO_mwi_18 dao;
	
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	@GetMapping("/employeeHomePage")
	public String openPage(ModelAndView view, Principal principal) {
		
		
		view.setViewName("employeeHomePage");
		
		view.addObject("name","employee");
		
		return view.getViewName();
	}
	
	
	@ModelAttribute("unassignedTickets")
	public List<Ticket_untitled> getUnassignedTickets(){
		
		return dao.getAllUnassignedTickets();
	}
	
	@ModelAttribute("assignedTickets")
	public List<Ticket_untitled> getAssignedTickets(Principal princ){
		
		return dao.getAssignedTickets(authenticatedUser.getLoggedInUserContext(princ).getUsername());
	}
}
