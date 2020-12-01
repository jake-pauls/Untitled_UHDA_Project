package com.csis3275.controller_untitled;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;
import com.csis3275.utility_untitled.SlackRestUtilityService_jpa_66;
import com.csis3275.utility_untitled.UserAuthenticationUtilities_untitled;




@Controller
public class EmployeePageController_mwi_18 {
	
	/**
	 * wires the ticketDisplayDao
	 */
	@Autowired
	TicketDisplayDAO_mwi_18 dao;
	
	/**
	 * wires the authenticated user
	 */
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	@Autowired
	SlackRestUtilityService_jpa_66 slackService;
	
	/**
	 * model for ticket under the name ticket
	 * @return new ticket 
	 */
	@ModelAttribute("ticket")
	public Ticket_untitled newTicket() {
		return new Ticket_untitled();
	}
	
	
	/**
	 * GET request mapped to the employee's page view
	 * @param principal Object for the logged in user context
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return ModelAndView object containing the view name for the user home page
	 */
	@GetMapping("/employeeHomePage")
	public ModelAndView openPage(ModelAndView view, Principal principal) {
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		view.addObject("loggedInUser", loggedInUser);
		
		// Check employee user Slack association and update Slack icon status in UI
		HashMap<String, String> slackStatus = slackService.verifySlackAssociation(loggedInUser);
		view = slackService.updateSlackIconStatusForView(slackStatus, view);
		
		view.setViewName("employeeHomePage");
		List<Ticket_untitled> myList = dao.getAssignedTickets(loggedInUser.getUsername(),"dateOpened");
		view.addObject("assignedTickets",myList);
		List<Ticket_untitled> unAssignedList = dao.getAllUnassignedTickets("dateOpened");
		view.addObject("unAssignedTickets",unAssignedList);
		List<Ticket_untitled> topPriorityTickets = dao.getTopPriorityTickets(loggedInUser.getUsername());
		view.addObject("topPriorityTickets", topPriorityTickets);
		List<Ticket_untitled> mostRecentUnassignedTickets = dao.getMostRecentUnassignedTickets();
		view.addObject("mostRecentUnassignedTickets", mostRecentUnassignedTickets);

		
		return view;
	}
	
	
	/**
	 * Sorts the tickets as specified
	 * @param order The order of the display
	 * @param view The modelview used to set upview and models
	 * @param principal Used to verify logged in user
	 * @return ModelandView object containing info to display page
	 */
	@GetMapping("/sort")
	public ModelAndView sortTickets(String order,ModelAndView view,Principal principal) {
		view.setViewName("employeeHomePage");
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		
		// Refresh Slack icon status 
		HashMap<String, String> slackStatus = slackService.verifySlackAssociation(loggedInUser);
		view = slackService.updateSlackIconStatusForView(slackStatus, view);
		
		List<Ticket_untitled> myList = dao.getAssignedTickets(loggedInUser.getUsername(),order);
		view.addObject("assignedTickets",myList);
		
		myList = dao.getAllUnassignedTickets(order);
		view.addObject("unAssignedTickets",myList);
		
		List<Ticket_untitled> topPriorityTickets = dao.getTopPriorityTickets(loggedInUser.getUsername());
		view.addObject("topPriorityTickets", topPriorityTickets);
		List<Ticket_untitled> mostRecentUnassignedTickets = dao.getMostRecentUnassignedTickets();
		view.addObject("mostRecentUnassignedTickets", mostRecentUnassignedTickets);
		
		return view;
	}
	
	
	
	
	
	/**
	 * 
	 * @return list of status options
	 */
	@ModelAttribute("statusList")
	public List<String> getStatusList(){
		List<String> myList = new ArrayList<String>();
		myList.add(Ticket_untitled.TICKET_STATUS_OPEN);
		myList.add(Ticket_untitled.TICKET_STATUS_IN_PROGRESS);
		myList.add(Ticket_untitled.TICKET_STATUS_RESOLVED);
		myList.add(Ticket_untitled.TICKET_STATUS_CLOSED);
		return myList;
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

	
	/**
	 * 
	 * @return list of priority options
	 */
	@ModelAttribute("priorityList")
	public List<String> getpriorityList(){
		List<String> myList = new ArrayList<String>();
		myList.add(Ticket_untitled.TICKET_PRIORITY_TRIVIAL);
		myList.add(Ticket_untitled.TICKET_PRIORITY_LOW);
		myList.add(Ticket_untitled.TICKET_PRIORITY_NORMAL);
		myList.add(Ticket_untitled.TICKET_PRIORITY_HIGH);
		myList.add(Ticket_untitled.TICKET_PRIORITY_CRITICAL);
		return myList;
	}
	
	/**
	 * Adds employee list to the model
	 * @param modelMap
	 * 
	 */
	@ModelAttribute("employeeList")
	public void employeeAdminList(ModelMap modelMap){
		List<User_untitled> employeeList = dao.getListOfEmployeesAndAdmins();
		modelMap.addAttribute("employeeList", employeeList);
	}
}
