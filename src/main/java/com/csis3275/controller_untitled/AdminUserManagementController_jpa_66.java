package com.csis3275.controller_untitled;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.AdminUserManagementDAOImpl_jpa_66;
import com.csis3275.dao_untitled.EmailServiceImpl_untitled;
import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;
import com.csis3275.model_untitled.PasswordReset_gpo_20;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Oct 25, 2020
 * AdminUserManagementController_untitled.java
 * com.csis3275.controller_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Controller
public class AdminUserManagementController_jpa_66 {

	// Error message constants
	private final String DUPLICATE_USERNAME_ERROR_MESSAGE = "The username you entered already exists, please try again";
	private final String DUPLICATE_EMAIL_ERROR_MESSAGE = "The email you entered already exists, please try again";
	
	// Success message constants
	private final String INSERT_USER_SUCCESS_MESSAGE = "User was successfully added to the workspace";
	private final String UPDATE_USER_SUCCESS_MESSAGE = "User was successfully updated in the workspace";
	private final String DELETE_USER_SUCCESS_MESSAGE = "User was successfully deleted from the workspace";
	
	@Autowired
	AdminUserManagementDAOImpl_jpa_66 adminUserManagementDAOImpl;
	
	/**
	 * DAO and email service to trigger Administrator password resets
	 */
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	@Autowired
	private EmailServiceImpl_untitled emailService;
	
	/**
	 * Model attribute bound to the 'User_untitled' object
	 * @return The user Object, binding the model attribute "user"
	 */
	@ModelAttribute("user")
	public User_untitled setupUserModelAttribute() {
		return new User_untitled();
	}
	
	/**
	 * GET request mapping to display workspace users from database
	 * @param session Currently observed HTTP session
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return The ModelAndView object containing the retrieved user list and a redirect to the AdminUserManagement view
	 */
	@RequestMapping(value = "/AdminUserManagement", method = RequestMethod.GET)
	public ModelAndView showWorkspaceUsers(HttpSession session, ModelAndView modelAndView) {
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("AdminUserManagement");
		return modelAndView;
	}
	
	/**
	 * POST request mapping for creating new workspace users and adding them to the database
	 * @param createdUser New 'User_untitled' object created from the form, form field values are bound to their respective 'User_untitled' properties
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return The ModelAndView object containing a refreshed user list from the database and an success or error messages that may have been provoked during the operation
	 */
	@RequestMapping(value = "/AdminUserManagementCreateUser", method = RequestMethod.POST)
	public ModelAndView createWorkspaceUser(@ModelAttribute("user") User_untitled createdUser, ModelAndView modelAndView, HttpServletRequest request) {
		if(adminUserManagementDAOImpl.checkIfUsernameExists(createdUser.getUsername())) {
			if (adminUserManagementDAOImpl.checkIfEmailExists(createdUser.getEmail())) {
				adminUserManagementDAOImpl.createUser(createdUser);
				// Trigger password reset upon creating user
				adminResetUserPassword(createdUser.getEmail(), request);
				modelAndView.addObject("successMessage", INSERT_USER_SUCCESS_MESSAGE);
			} else {
				modelAndView.addObject("errorMessage", DUPLICATE_EMAIL_ERROR_MESSAGE);
			}
		} else {
			modelAndView.addObject("errorMessage", DUPLICATE_USERNAME_ERROR_MESSAGE);
		}
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("AdminUserManagement");
		return modelAndView;
	}
	
	/**
	 * POST request mapping for updating users within the workspace and appending those changes to the database
	 * @param userToUpdate New 'User_untitled' object created from the edited fields in the form, form field values are bound to their respective 'User_untitled' properties
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return The ModelAndView object containing a refreshed user list from the database and a success message for the operation
	 */
	@RequestMapping(value = "/AdminUserManagementUpdateUser", method = RequestMethod.POST)
	public ModelAndView handleUpdateUser(@ModelAttribute("user") User_untitled userToUpdate, ModelAndView modelAndView) {		
		adminUserManagementDAOImpl.updateUser(userToUpdate);
		modelAndView.addObject("successMessage", UPDATE_USER_SUCCESS_MESSAGE);
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("AdminUserManagement");
		return modelAndView;
	}
	
	/**
	 * POST request mapping for deleting users within the workspace and appending the changes to the database
	 * @param userToDelete New 'User_untitled' object created containing the username for the user to be deleted
	 * @param modelAndView Object containing the model and view attributes in scope
	 * @return The ModelAndView object containing a refreshes user list from the database and a success message for the operation
	 */
	@RequestMapping(value = "/AdminUserManagementDeleteUser", method = RequestMethod.POST)
	public ModelAndView handleDeleteUser(@ModelAttribute("user") User_untitled userToDelete, ModelAndView modelAndView) {		
		adminUserManagementDAOImpl.deleteUser(userToDelete);
		modelAndView.addObject("successMessage", DELETE_USER_SUCCESS_MESSAGE);
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("AdminUserManagement");
		return modelAndView;
	}
	
	/**
	 * Calls the email service to send a account confirmation email whenever a new user is added to the workspace
	 * @param email String containing the destination email address for the account confirmation
	 * @param request HttpServletRequest object containing the request data for the HttpSession
	 */
	private void adminResetUserPassword(String email, HttpServletRequest request) {
		// Create a new password reset object for the passed model
		PasswordReset_gpo_20 resetPassword = new PasswordReset_gpo_20();
		resetPassword.setEmail(email);
		User_untitled user = passwordResetDAO.checkUserEmailExists(resetPassword.getEmail());
		if(user !=null) {
			// Generate random 36-character string token for reset password 
			resetPassword.setResetToken(UUID.randomUUID().toString());
			user.setResetToken(resetPassword.getResetToken());
			passwordResetDAO.addResetTokenToUser(user);
			String resetPageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();
			// Email message with reset link using the email setup in the application.properties
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("uhda.untitled.csis3275@gmail.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Action Required: Account Created in UHDA, Account Confirmation Required");
			passwordResetEmail.setText("To confirm your account and set your login password, click the link below:\n" 
					+ resetPageUrl + "/reset?resetToken=" + user.getResetToken());
			emailService.sendEmail(passwordResetEmail);
		} 
	}
}
