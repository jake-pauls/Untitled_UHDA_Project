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

import com.csis3275.dao_untitled.HardwareDAO_gpo_20;
import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.HardwareList_gpo_20;
import com.csis3275.model_untitled.HardwareTypes_gpo_20;
import com.csis3275.dao_untitled.CommentDAO_mwi_18;
import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Comment_mwi_18;
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
	TicketDisplayDAO_mwi_18 ticketDisplayDao;
	
	@Autowired
	CommentDAO_mwi_18 commentDao;
	
	@Autowired
	HardwareDAO_gpo_20 hardwareDAO;
	
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
	
	@ModelAttribute("comment")
	public Comment_mwi_18 setUpName() {
		return new Comment_mwi_18();
	}
	
	
	/**
	 * GET request mapped to the employee's page view
	 * @param principal Object for the logged in user context
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return ModelAndView object containing the view name for the user home page
	 */
	@GetMapping("/employeeHomePage")
	public ModelAndView openPage(ModelAndView view, Principal principal) {
		view.setViewName("employeeHomePage");
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		view.addObject("loggedInUser", loggedInUser);
		
		// Check employee user Slack association and update Slack icon status in UI
		HashMap<String, String> slackStatus = slackService.verifySlackAssociation(loggedInUser);
		view = slackService.updateSlackIconStatusForView(slackStatus, view);

		
		view.setViewName("employeeHomePage");
		List<Ticket_untitled> myList = ticketDisplayDao.getAssignedTickets(loggedInUser.getUsername(),"dateOpened");
		for (Ticket_untitled ticket_untitled : myList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("assignedTickets",myList);
		List<Ticket_untitled> unAssignedList = ticketDisplayDao.getAllUnassignedTickets("dateOpened");
		for (Ticket_untitled ticket_untitled : unAssignedList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("unAssignedTickets",unAssignedList);
		List<Ticket_untitled> topPriorityTickets = ticketDisplayDao.getTopPriorityTickets(loggedInUser.getUsername());
		for (Ticket_untitled ticket_untitled : topPriorityTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("topPriorityTickets", topPriorityTickets);
		List<Ticket_untitled> mostRecentUnassignedTickets = ticketDisplayDao.getMostRecentUnassignedTickets();
		for (Ticket_untitled ticket_untitled : mostRecentUnassignedTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("mostRecentUnassignedTickets", mostRecentUnassignedTickets);

		// Hardware Request Data
		HardwareList_gpo_20 hardware = new HardwareList_gpo_20();
		view.addObject("hardware", hardware);
		List<HardwareTypes_gpo_20> hardwareTypeList = hardwareDAO.getListOfHardwareAvailable();
		view.addObject("hardwareTypeList",hardwareTypeList);
		List<HardwareList_gpo_20> hardwareAssignedList = hardwareDAO.getListOfHardwareAssigned();
		view.addObject("hardwareAssignedList",hardwareAssignedList);
		HardwareTypes_gpo_20 hardwareType = new HardwareTypes_gpo_20();
		view.addObject("hardwareType",hardwareType);
		HardwareList_gpo_20 assignedHardware = new HardwareList_gpo_20();
		view.addObject("assignedHardware",assignedHardware);
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
		List<Ticket_untitled> myList = ticketDisplayDao.getAssignedTickets(authenticatedUser.getLoggedInUserContext(principal).getUsername(),order);
		
		for (Ticket_untitled ticket_untitled : myList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		
		view.addObject("assignedTickets",myList);
		
		myList = ticketDisplayDao.getAllUnassignedTickets(order);
		for (Ticket_untitled ticket_untitled : myList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("unAssignedTickets",myList);
		
		myList = ticketDisplayDao.getAllUnassignedTickets(order);
		for (Ticket_untitled ticket_untitled : myList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		List<Ticket_untitled> topPriorityTickets = ticketDisplayDao.getTopPriorityTickets(loggedInUser.getUsername());
		for (Ticket_untitled ticket_untitled : topPriorityTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("topPriorityTickets", topPriorityTickets);
		List<Ticket_untitled> mostRecentUnassignedTickets = ticketDisplayDao.getMostRecentUnassignedTickets();
		for (Ticket_untitled ticket_untitled : mostRecentUnassignedTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		view.addObject("mostRecentUnassignedTickets", mostRecentUnassignedTickets);
		
		
		
		for (Ticket_untitled ticket_untitled : myList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		
		// Hardware Request Data
		HardwareList_gpo_20 hardware = new HardwareList_gpo_20();
		view.addObject("hardware", hardware);
		List<HardwareTypes_gpo_20> hardwareTypeList = hardwareDAO.getListOfHardwareAvailable();
		view.addObject("hardwareTypeList",hardwareTypeList);
		List<HardwareList_gpo_20> hardwareAssignedList = hardwareDAO.getListOfHardwareAssigned();
		view.addObject("hardwareAssignedList",hardwareAssignedList);
		HardwareTypes_gpo_20 hardwareType = new HardwareTypes_gpo_20();
		view.addObject("hardwareType",hardwareType);
		HardwareList_gpo_20 assignedHardware = new HardwareList_gpo_20();
		view.addObject("assignedHardware",assignedHardware);
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

	
	/**
	 * 
	 * @return list of category options
	 */
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
		List<User_untitled> employeeList = ticketDisplayDao.getListOfEmployeesAndAdmins();
		modelMap.addAttribute("employeeList", employeeList);
	}
	@ModelAttribute("hardwareNameList")
	public void nameOfHardware(ModelMap modelMap){
		List<HardwareTypes_gpo_20> hardwareNameList = hardwareDAO.getListOfHardwareAvailable();
		modelMap.addAttribute("hardwareNameList", hardwareNameList);
	}

}
