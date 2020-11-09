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
	private final String TICKET_STATUS_SUCCESS_MESSAGE = "Ticket status has been updated succesfully";
	
	/**
	 * wire up and declare the ticket action sql class
	 */
	@Autowired
	TicketActionsDAO_Impl_gpo_20 ticketActionsDAOImpl;
	
	/**
	 * wire up the email services class
	 */
	@Autowired
	private EmailServiceImpl_untitled emailService;
	
	/**
	 * Model attribute bound to the User_untitled object
	 * @return the user object, binding the model attribute "user"
	 */
	@ModelAttribute("user")
	public User_untitled setupUserModelAttribute() {
		return new User_untitled();
	}
	/**
	 * Model attribute bound to the Ticket_untitled object
	 * @return the ticket object, binding the model attribute "ticket"
	 */
	@ModelAttribute("ticket")
	public Ticket_untitled setupTicketModelAttribute() {
		return new Ticket_untitled();
	}
	
	/**
	 * POST request mapping for assigning a ticket.
	 * @param ticketToAssign New 'Ticket_untitled' object created from the ticket being edited 
	 * @param modelAndView object containing the model and view attributes in scope
	 * @return 
	 */
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
	
	/**
	 * POST request mapping for changing a tickets status.
	 * @param ticketToAssign New 'Ticket_untitled' object created from the ticket being actioned 
	 * @param modelAndView object containing the model and view attributes in scope
	 * @return 
	 */
	@RequestMapping(value = "/ChangeTicketStatus", method = RequestMethod.POST)
	public ModelAndView handleChangingTicketStatus(@ModelAttribute("ticket") Ticket_untitled ticketToAssign, ModelAndView modelAndView) {
		ticketActionsDAOImpl.changeTicketStatus(ticketToAssign);
		User_untitled userProfile = ticketActionsDAOImpl.getUserProfileByUsername(ticketToAssign.getUsername());
		User_untitled assigneeProfile = ticketActionsDAOImpl.getAssigneeProfileByUsername(ticketToAssign.getAssignee());	
		String subjectOfEmail = "Ticket Number: " + ticketToAssign.getTicketID() + " status has been updated to " + ticketToAssign.getStatus();
		String emailText = "Your ticket status has been updated to " + ticketToAssign.getStatus() + " please review your ticket if further action is required";
		modelAndView.addObject("successMessage", TICKET_STATUS_SUCCESS_MESSAGE);
		ticketActionEmail(userProfile.getEmail(), assigneeProfile.getEmail(), subjectOfEmail, emailText);
		List<Ticket_untitled> ticketList = ticketActionsDAOImpl.getAllTickets();
		modelAndView.addObject("ticketList", ticketList);
		modelAndView.setViewName("TicketDisplay");
		return modelAndView;
	}
	
	/**
	 * POST request mapping for changing a tickets priority.
	 * @param ticketToAssign New 'Ticket_untitled' object created from the ticket being actioned 
	 * @param modelAndView object containing the model and view attributes in scope
	 * @return 
	 */
	@RequestMapping(value = "/ChangeTicketPriority", method = RequestMethod.POST)
	public ModelAndView handleChangingTicketPriority(@ModelAttribute("ticket") Ticket_untitled ticketToAssign, ModelAndView modelAndView) {
		ticketActionsDAOImpl.changeTicketPriority(ticketToAssign);
		User_untitled userProfile = ticketActionsDAOImpl.getUserProfileByUsername(ticketToAssign.getUsername());
		User_untitled assigneeProfile = ticketActionsDAOImpl.getAssigneeProfileByUsername(ticketToAssign.getAssignee());	
		String subjectOfEmail = "Ticket Number: " + ticketToAssign.getTicketID() + " priority has been changed to " + ticketToAssign.getPriority();
		String emailText = "Your ticket priority has been updated to " + ticketToAssign.getStatus() + " please review your ticket if further action is required, we look forward to assisting you as soon as possible";
		modelAndView.addObject("successMessage", TICKET_STATUS_SUCCESS_MESSAGE);
		ticketActionEmail(userProfile.getEmail(), assigneeProfile.getEmail(), subjectOfEmail, emailText);
		List<Ticket_untitled> ticketList = ticketActionsDAOImpl.getAllTickets();
		modelAndView.addObject("ticketList", ticketList);
		modelAndView.setViewName("TicketDisplay");
		return modelAndView;
	}
	
	/**
	 * Email service to send update emails based on the ticket action performed.
	 * @param userEmail, gathered from the username from the ticket that is being actioned
	 * @param assigneeEmail, gathered from the assignee from the ticket that is being actioned
	 * @param subjectLine, used to provide a customized subject line based on the action performed on the ticket
	 * @param message, used to provide a customized message text based on the action performed.
	 */
	public void ticketActionEmail(String userEmail, String assigneeEmail, String subjectLine, String message) {
		SimpleMailMessage ticketActionEmail = new SimpleMailMessage();
		ticketActionEmail.setFrom("uhda.untitled.csis3275@gmail.com");
		ticketActionEmail.setCc(assigneeEmail);
		ticketActionEmail.setSubject(subjectLine);
		ticketActionEmail.setText(message);
		emailService.sendEmail(ticketActionEmail);
	}
}