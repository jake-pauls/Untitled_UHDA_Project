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

import com.csis3275.dao_untitled.TicketManagementDAOImpl_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;
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
	
	@RequestMapping(value = "/createTicket", method = RequestMethod.POST) 
	public RedirectView createTicket(@RequestParam("redirectUrl") String redirectUrl, @ModelAttribute("ticket") Ticket_untitled createdTicket, RedirectAttributes redirectAttributes, HttpSession session, ModelAndView modelAndView, Principal principal) {
		// Determine correct url
		RedirectView redirectView =  new RedirectView("/"+redirectUrl, true);
		if(ticketManagementDAOImpl.createTicket(createdTicket)) {
			redirectAttributes.addFlashAttribute("successMessage", "Ticket created successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to create ticket, please try again");
		}
		return redirectView;
	}
	
}
