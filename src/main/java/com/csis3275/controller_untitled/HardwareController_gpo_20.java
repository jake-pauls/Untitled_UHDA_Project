package com.csis3275.controller_untitled;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.TicketActionsDAO_Impl_gpo_20;
import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;
import com.csis3275.utility_untitled.UserAuthenticationUtilities_untitled;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Nov 28, 2020
 * HardwareController_gpo_20.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 *
 */

@Controller
public class HardwareController_gpo_20 {

		// Error messages

		// Success messages
		private final String TICKET_ASSIGNED_SUCCESS_MESSAGE = "";

		/**
		 * wire up and declare the ticket action sql class
		 */
		@Autowired
		TicketActionsDAO_Impl_gpo_20 ticketActionsDAOImpl;

		/**
		 * wire up and declare the ticket display sql class
		 */
		@Autowired
		TicketDisplayDAO_mwi_18 dao;
		
		/**
		 * wire up the authenticated user
		 */
		@Autowired
		UserAuthenticationUtilities_untitled authenticatedUser;
		
		/**
		 * wire up the email services class
		 */
		@Autowired
		private EmailServiceImpl_untitled emailService;

		/**
		 * Model attribute bound to the User_untitled object
		 * 
		 * @return the user object, binding the model attribute "user"
		 */
		@ModelAttribute("user")
		public User_untitled setupUserModelAttribute() {
			return new User_untitled();
		}

		/**
		 * Model attribute bound to the Ticket_untitled object
		 * 
		 * @return the ticket object, binding the model attribute "ticket"
		 */

		@RequestMapping(value = "/AssignHardware", method = RequestMethod.POST)
		public RedirectView handleAssigningHardware(@ModelAttribute("ticket") Ticket_untitled hardwareToAssign,
				RedirectAttributes redirectAttributes) {
			ticketActionsDAOImpl.assignTicket(hardwareToAssign);
			User_untitled userProfile = ticketActionsDAOImpl.getUserProfileByUsername(hardwareToAssign.getUsername());
			User_untitled assigneeProfile = ticketActionsDAOImpl.getAssigneeProfileByUsername(hardwareToAssign.getAssignee());
			String subjectOfEmail = "Ticket Number: " + hardwareToAssign.getTicketID() + " has been assigned to "
					+ assigneeProfile.getFirstName() + " " + assigneeProfile.getLastName();
			String emailText = "Your ticket has now been assigned, " + assigneeProfile.getFirstName()
					+ " will be addressing your issue or concern and will update the ticket as progress is made";
			ticketActionEmail(userProfile.getEmail(), assigneeProfile.getEmail(), subjectOfEmail, emailText);
			redirectAttributes.addFlashAttribute("successMessage", TICKET_ASSIGNED_SUCCESS_MESSAGE);
			RedirectView redirectView = new RedirectView("/employeeHomePage", true);
			return redirectView;
		}
	
		/**
		 * Model attribute bound to the Ticket_untitled object
		 * 
		 * @return the ticket object, binding the model attribute "ticket"
		 */
		@ModelAttribute("ticket")
		public Ticket_untitled newTicket() {
			return new Ticket_untitled();
		}
		

		/**
		 * Email service to send update emails based on the ticket action performed.
		 * 
		 * @param userEmail,     gathered from the username from the ticket that is
		 *                       being actioned
		 * @param assigneeEmail, gathered from the assignee from the ticket that is
		 *                       being actioned
		 * @param subjectLine,   used to provide a customized subject line based on the
		 *                       action performed on the ticket
		 * @param message,       used to provide a customized message text based on the
		 *                       action performed.
		 */
		public void ticketActionEmail(String userEmail, String assigneeEmail, String subjectLine, String message) {
			SimpleMailMessage ticketActionEmail = new SimpleMailMessage();
			ticketActionEmail.setFrom("uhda.untitled.csis3275@gmail.com");
			ticketActionEmail.setTo(userEmail);
			if (assigneeEmail != null) {
				ticketActionEmail.setCc(assigneeEmail);
			}
			ticketActionEmail.setSubject(subjectLine);
			ticketActionEmail.setText(message);
			emailService.sendEmail(ticketActionEmail);
		}
}

