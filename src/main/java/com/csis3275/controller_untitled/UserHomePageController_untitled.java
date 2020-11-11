package com.csis3275.controller_untitled;

import java.security.Principal;
import java.util.ArrayList;
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
	
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	@Autowired
	TicketDisplayDAO_mwi_18 dao;
	
	
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
	
	@GetMapping("/sortUser")
	public ModelAndView sortTickets(String order,ModelAndView view,Principal principal) {
		view.setViewName("UserHomePage");
		List<Ticket_untitled> myList = dao.getCreatedTickets(authenticatedUser.getLoggedInUserContext(principal).getUsername(),order);
		
		view.addObject("createdList",myList);
		view.addObject("loggedInUser", authenticatedUser.getLoggedInUserContext(principal));
		
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
