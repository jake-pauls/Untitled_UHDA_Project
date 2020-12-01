package com.csis3275.controller_untitled;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.csis3275.dao_untitled.SlackAssociationDAOImpl_jpa_66;
import com.csis3275.dao_untitled.TicketManagementDAOImpl_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;
import com.csis3275.utility_untitled.SlackRestUtilityService_jpa_66;
import com.csis3275.utility_untitled.UserAuthenticationUtilities_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 9, 2020
 * CreateTicketController_jpa_66.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Controller
public class CreateTicketController_jpa_66 {

	@Autowired 
	TicketManagementDAOImpl_jpa_66 ticketManagementDAOImpl;
	
	@Autowired
	UserAuthenticationUtilities_untitled authenticatedUser;
	
	@Autowired
	SlackRestUtilityService_jpa_66 slackService;
	
	/**
	 * POST method that creates a ticket within the database
	 * @param redirectUrl String HTTP parameter indicating the view to be redirected to once the create operation is completed
	 * @param createdTicket 'Ticket_untitled' object with values bound from the data entered by the user in the 'Create Ticket' form
	 * @param principal Principal object containing authentication details for the currently logged in user
	 * @return A 'RedirectView' object containing the parameterized URL to redirect to, as well as any applicable success or error messages
	 */
	@RequestMapping(value = "/createTicket", method = RequestMethod.POST) 
	public RedirectView createTicket(@RequestParam("redirectUrl") String redirectUrl, @ModelAttribute("ticket") Ticket_untitled createdTicket, RedirectAttributes redirectAttributes, HttpSession session, ModelAndView modelAndView, Principal principal) {
		// Determine correct url
		RedirectView redirectView =  new RedirectView("/"+redirectUrl, true);
		
		// All new User-created tickets should be initialized to have the priority 'Under Review'
		createdTicket.setPriority(Ticket_untitled.TICKET_PRIORITY_UNDER_REVIEW);
		
		if(ticketManagementDAOImpl.createTicket(createdTicket)) {
			redirectAttributes.addFlashAttribute("successMessage", "Ticket created successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to create ticket, please try again");
		}
		
		// Post a created ticket notification to Slack
		User_untitled loggedInUser = authenticatedUser.getLoggedInUserContext(principal);
		slackService.createTicketNotification(loggedInUser, createdTicket);
		
		return redirectView;
	}
	
}
