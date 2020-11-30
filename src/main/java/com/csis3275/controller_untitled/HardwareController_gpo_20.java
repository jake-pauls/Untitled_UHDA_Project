package com.csis3275.controller_untitled;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.HardwareDAO_gpo_20;
import com.csis3275.dao_untitled.TicketActionsDAO_Impl_gpo_20;
import com.csis3275.dao_untitled.TicketDisplayDAO_mwi_18;
import com.csis3275.model_untitled.HardwareList_gpo_20;
import com.csis3275.model_untitled.HardwareTypes_gpo_20;
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
		private final String HARDWARE_ASSIGNED_SUCCESS_MESSAGE = "Hardware has been assigned successfully, the ticket can now be closed!";
		private final String HARDWARE_TYPE_CREATED_SUCCESS_MESSAGE = "New Hardware Type created";
		private final String HARDWARE_TYPE_DELETED_SUCCESS_MESSAGE = "Hardware Type has been deleted";
		private final String HARDWARE_RETURNED_SUCCESS_MESSAGE = "Hardware has been returned";

		/**
		 * wire up and declare the ticket action sql class
		 */
		@Autowired
		HardwareDAO_gpo_20 hardwareDAO;

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
		public RedirectView handleAssigningHardware(@ModelAttribute("hardware") HardwareList_gpo_20 hardwareToAssign,
				RedirectAttributes redirectAttributes) {
			hardwareDAO.assignHardware(hardwareToAssign);
			redirectAttributes.addFlashAttribute("successMessage", HARDWARE_ASSIGNED_SUCCESS_MESSAGE);
			RedirectView redirectView = new RedirectView("/employeeHomePage", true);
			return redirectView;
		}
		
		@RequestMapping(value = "/CreateHardwareType", method = RequestMethod.POST)
		public RedirectView createHardwareType(@RequestParam("redirectUrl") String redirectUrl, @ModelAttribute("hardwareType") HardwareTypes_gpo_20 hardwareType,
				RedirectAttributes redirectAttributes, HttpSession session) {
			hardwareDAO.addNewHardWareType(hardwareType);
			redirectAttributes.addFlashAttribute("successMessage", HARDWARE_TYPE_CREATED_SUCCESS_MESSAGE);
			RedirectView redirectView = new RedirectView("/"+redirectUrl, true);
			return redirectView;
		}
		/* commented out the delete hardware type button as it causes referential integrity issues with DB
		@RequestMapping(value = "/DeleteHardwareType", method = RequestMethod.POST)
		public RedirectView deleteHardwareType(@RequestParam("redirectUrl") String redirectUrl, @ModelAttribute("hardwareType") HardwareTypes_gpo_20 hardwareTypeToDelete,
				RedirectAttributes redirectAttributes, HttpSession session) {
			hardwareDAO.deleteHardwareType(hardwareTypeToDelete);
			redirectAttributes.addFlashAttribute("successMessage", HARDWARE_TYPE_DELETED_SUCCESS_MESSAGE);
			RedirectView redirectView = new RedirectView("/"+redirectUrl, true);
			return redirectView;
		}
	*/
		@RequestMapping(value = "/ReturnHardware", method = RequestMethod.POST)
		public RedirectView deleteHardwareType(@RequestParam("redirectUrl") String redirectUrl, @ModelAttribute("assignedHardware") HardwareList_gpo_20 hardwareToReturn,
				RedirectAttributes redirectAttributes, HttpSession session) {
			hardwareDAO.returnHardware(hardwareToReturn);
			redirectAttributes.addFlashAttribute("successMessage", HARDWARE_RETURNED_SUCCESS_MESSAGE);
			RedirectView redirectView = new RedirectView("/"+redirectUrl, true);
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
		 * Model attribute bound to the HardwareList object
		 * 
		 * @return the ticket object, binding the model attribute "hardware"
		 */
		@ModelAttribute("hardware")
		public HardwareList_gpo_20 newHardwareAssigned() {
			return new HardwareList_gpo_20();
		}
		
		@ModelAttribute("hardwareType")
		public HardwareTypes_gpo_20 setupHardwareTypeModelAttribute() {
			return new HardwareTypes_gpo_20();
		}
		


}

