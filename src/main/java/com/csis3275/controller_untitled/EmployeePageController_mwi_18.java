package com.csis3275.controller_untitled;

import java.io.Console;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;
import com.csis3275.utility_untitled.UserAuthenticationUtilities_untitled;



@Controller
public class EmployeePageController_mwi_18 {
	
	@Autowired
	TicketDisplayDAO_mwi_18 dao;
	
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	@ModelAttribute("ticket")
	public Ticket_untitled newTicket() {
		return new Ticket_untitled();
	}
	
	@GetMapping("/employeeHomePage")
	public ModelAndView openPage(ModelAndView view, Principal principal) {
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		view.addObject("loggedInUser", loggedInUser);
		
		view.setViewName("employeeHomePage");
		List<Ticket_untitled> myList = dao.getAssignedTickets(loggedInUser.getUsername(),"dateOpened");
		view.addObject("assignedTickets",myList);
		
		return view;
	}
	
	@GetMapping("/sort")
	public ModelAndView sortTickets(String order,ModelAndView view,Principal principal) {
		view.setViewName("employeeHomePage");
		List<Ticket_untitled> myList = dao.getAssignedTickets(authenticatedUser.getLoggedInUserContext(principal).getUsername(),order);
		
		view.addObject("assignedTickets",myList);
		return view;
	}
	
	
	@ModelAttribute("unassignedTickets")
	public List<Ticket_untitled> getUnassignedTickets(){
		
		return dao.getAllUnassignedTickets();
	}
	
	
	
	
	@ModelAttribute("statusList")
	public List<String> getStatusList(){
		List<String> myList = new ArrayList<String>();
		myList.add(Ticket_untitled.TICKET_STATUS_OPEN);
		myList.add(Ticket_untitled.TICKET_STATUS_IN_PROGRESS);
		myList.add(Ticket_untitled.TICKET_STATUS_RESOLVED);
		myList.add(Ticket_untitled.TICKET_STATUS_CLOSED);
		return myList;
	}
	
	@ModelAttribute("priorityList")
	public List<String> getPriorityList(){
		List<String> priorityList = new ArrayList<String>();
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_CRITICAL);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_HIGH);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_NORMAL);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_LOW);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_TRIVIAL);
		return priorityList;
	}
	
	@ModelAttribute("categoryList")
	public List<String> getCategoryList(){
		List<String> categoryList = new ArrayList<String>();
		categoryList.add(Ticket_untitled.TICKET_CATEGORY_GENERAL);
		categoryList.add(Ticket_untitled.TICKET_CATEGORY_HARDWARE);
		categoryList.add(Ticket_untitled.TICKET_CATEGORY_INSTALLATION_REQUEST);
		categoryList.add(Ticket_untitled.TICKET_CATEGORY_INTERNET);
		categoryList.add(Ticket_untitled.TICKET_CATEGORY_SOFTWARE);
		return categoryList;
	}
}
