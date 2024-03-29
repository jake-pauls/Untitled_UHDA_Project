package com.csis3275.controller_untitled;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.SlackAssociationDAOImpl_jpa_66;
import com.csis3275.dao_untitled.CommentDAO_mwi_18;
import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Comment_mwi_18;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;
import com.csis3275.utility_untitled.SlackRestUtilityService_jpa_66;
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
	
	/**
	 * wires the authenticated user
	 */
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	/**
	 * wires the ticketDisplayDao
	 */
	@Autowired
	TicketDisplayDAO_mwi_18 dao;
	
	@Autowired
	SlackAssociationDAOImpl_jpa_66 slackAssociationDAO;
	
	@Autowired
	SlackRestUtilityService_jpa_66 slackService;
	
	@Autowired
	CommentDAO_mwi_18 commentDao;
	
	/**
	 * model for ticket under the name ticket
	 * @return new ticket 
	 */
	@ModelAttribute("ticket")
	public Ticket_untitled getTicketSetUp() {
		return new Ticket_untitled();
	}
	
	@ModelAttribute("comment")
	public Comment_mwi_18 setUpName() {
		return new Comment_mwi_18();
	}
	
	
	/**
	 * GET request mapped to the user's home page view
	 * @param session Currently observed HTTP session
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return ModelAndView object containing the view name for the user home page
	 */
	@RequestMapping(value = "/UserHomePage", method = RequestMethod.GET)	
	public ModelAndView userHomePage(HttpSession session, ModelAndView modelAndView, Principal principal) {
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		String loggedInUsername = loggedInUser.getUsername();
		
		// Check employee user Slack association and update Slack icon status in UI
		HashMap<String, String> slackStatus = slackService.verifySlackAssociation(loggedInUser);
		modelAndView = slackService.updateSlackIconStatusForView(slackStatus, modelAndView);

		modelAndView.addObject("loggedInUser", loggedInUser);
		modelAndView.setViewName("UserHomePage");
		
		List<Ticket_untitled> myTickets = dao.getCreatedTickets(loggedInUsername, "dateOpened");
		List<Ticket_untitled> mostRecentTickets = dao.getMostRecentTickets(loggedInUsername);
		
		for (Ticket_untitled ticket_untitled : myTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		for (Ticket_untitled ticket_untitled : mostRecentTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		modelAndView.addObject("createdList",myTickets);
		modelAndView.addObject("mostRecentTickets", mostRecentTickets);
		
		return modelAndView;
	}
	
	/**
	 * Sorts the tickets as specified
	 * @param order The order of the display
	 * @param view The modelview used to set upview and models
	 * @param principal Used to verify logged in user
	 * @return ModelandView object containing info to display page
	 */
	@GetMapping("/sortUser")
	public ModelAndView sortTickets(String order,ModelAndView view,Principal principal) {
		view.setViewName("UserHomePage");
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		
		// Refresh Slack icon status 
		HashMap<String, String> slackStatus = slackService.verifySlackAssociation(loggedInUser);
		view = slackService.updateSlackIconStatusForView(slackStatus, view);
		
		List<Ticket_untitled> ticketList = dao.getCreatedTickets(loggedInUser.getUsername(), order);
		List<Ticket_untitled> mostRecentTickets = dao.getMostRecentTickets(loggedInUser.getUsername());
		
		for (Ticket_untitled ticket_untitled : ticketList) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		for (Ticket_untitled ticket_untitled : mostRecentTickets) {
			ticket_untitled.setComments(commentDao.getComments(ticket_untitled.getTicketID()));
		}
		
		view.addObject("createdList",ticketList);
		view.addObject("mostRecentTickets", mostRecentTickets);
		view.addObject("loggedInUser", loggedInUser);
		
		
		
		return view;
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
	
	@ModelAttribute("priorityList")
	public List<String> getpriorityList(){
		List<String> priorityList = new ArrayList<String>();
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_TRIVIAL);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_LOW);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_NORMAL);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_HIGH);
		priorityList.add(Ticket_untitled.TICKET_PRIORITY_CRITICAL);
		return priorityList;
	}
	
}
