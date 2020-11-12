package com.csis3275.controller_untitled;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Ticket_untitled;
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
	
	/**
	 * model for ticket under the name ticket
	 * @return new ticket 
	 */
	@ModelAttribute("ticket")
	public Ticket_untitled getTicketSetUp() {
		return new Ticket_untitled();
	}
	
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
		
		List<Ticket_untitled> myTickets = dao.getCreatedTickets(authenticatedUser.getLoggedInUserContext(principal).getUsername(), "dateOpened");
		modelAndView.addObject("createdList",myTickets);
		
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
		List<Ticket_untitled> myList = dao.getCreatedTickets(authenticatedUser.getLoggedInUserContext(principal).getUsername(),order);
		
		view.addObject("createdList",myList);
		view.addObject("loggedInUser", authenticatedUser.getLoggedInUserContext(principal));
		
		return view;
	}
	
	
	
}
