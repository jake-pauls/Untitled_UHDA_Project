package com.csis3275.controller_untitled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.TicketActionsDAO_Impl_gpo_20;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Nov 8, 2020
 * TicketActionsController_gpo_20.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 *
 */

@Controller
public class TicketActionsController_gpo_20 {
	//Error messages
	
	//Success messages
	private final String TICKET_ASSIGNED_SUCCESS_MESSAGE = "Ticket has been assigned succesfully";
	
	@Autowired
	TicketActionsDAO_Impl_gpo_20 ticketActionsDAOImpl;
	
	@Autowired
	private EmailServiceImpl_untitled emailService;
	
	@ModelAttribute("user")
	public User_untitled setupUserModelAttribute() {
		return new User_untitled();
	}
	@ModelAttribute("ticket")
	public Ticket_untitled setupTicketModelAttribute() {
		return new Ticket_untitled();
	}
	
	@RequestMapping(value = "/AssignTicket", method = RequestMethod.POST)
	public ModelAndView handleAssigningTicket(@ModelAttribute("ticket") Ticket_untitled ticketToAssign, ModelAndView modelAndView) {
		ticketActionsDAOImpl.assignTicket(ticketToAssign);
		User_untitled userProfile = ticketActionsDAOImpl.getUserProfileByUsername(ticketToAssign.getUsername());
		User_untitled assigneeProfile = ticketActionsDAOImpl.getAssigneeProfileByUsername(ticketToAssign.getAssignee());	
		String subjectOfEmail = "Ticket Number: " + ticketToAssign.getTicketID() + " has been assigned to " + assigneeProfile.getFirstName() + " "+ assigneeProfile.getLastName();
		String emailText = "Your ticket has now been assigned, " + assigneeProfile.getFirstName() + " will be addressing your issue or concern and will update the ticket as progress is made";
		modelAndView.addObject("successMessage", TICKET_ASSIGNED_SUCCESS_MESSAGE);
		ticketActionEmail(userProfile.getEmail(), assigneeProfile.getEmail(), subjectOfEmail, emailText);
		List<Ticket_untitled> ticketList = ticketActionsDAOImpl.getAllTickets();
		modelAndView.addObject("ticketList", ticketList);
		modelAndView.setViewName("TicketDisplay");
		return modelAndView;
	}
	
	public void ticketActionEmail(String userEmail, String assigneeEmail, String subjectLine, String message) {
		SimpleMailMessage ticketActionEmail = new SimpleMailMessage();
		ticketActionEmail.setFrom("uhda.untitled.csis3275@gmail.com");
		ticketActionEmail.setCc(assigneeEmail);
		ticketActionEmail.setSubject(subjectLine);
		ticketActionEmail.setText(message);
		emailService.sendEmail(ticketActionEmail);
	}
}
