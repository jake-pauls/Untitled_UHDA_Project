package com.csis3275.controller_untitled;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csis3275.dao_untitled.AdminUserManagementDAOImpl_untitled;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls
 * @date Oct 25, 2020
 * Untitled_UHDA_Project
 */

@Controller
public class AdminUserManagementController_untitled {

	// Error message constants
	private final String DUPLICATE_USERNAME_ERROR_MESSAGE = "The username you entered already exists, please try again";
	private final String DUPLICATE_EMAIL_ERROR_MESSAGE = "The email you entered already exists, please try again";
	
	// Success message constants
	private final String INSERT_USER_SUCCESS_MESSAGE = "User was successfully added to the workspace";
	private final String UPDATE_USER_SUCCESS_MESSAGE = "User was successfully updated in the workspace";
	private final String DELETE_USER_SUCCESS_MESSAGE = "User was successfully deleted from the workspace";
	
	@Autowired
	AdminUserManagementDAOImpl_untitled adminUserManagementDAOImpl;
	
	@ModelAttribute("user")
	public User_untitled setupUserModelAttribute() {
		return new User_untitled();
	}
	
	@RequestMapping(value = "/adminUserManagement", method = RequestMethod.GET)
	public ModelAndView showWorkspaceUsers(HttpSession session, ModelAndView modelAndView) {
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("adminUserManagement_untitled");
		return modelAndView;
	}
	
	@RequestMapping(value = "/adminUserManagementCreateUser", method = RequestMethod.POST)
	public ModelAndView createWorkspaceUser(@ModelAttribute("user") User_untitled createdUser, ModelAndView modelAndView) {
		if(adminUserManagementDAOImpl.checkIfUsernameExists(createdUser.getUsername())) {
			if (adminUserManagementDAOImpl.checkIfEmailExists(createdUser.getEmail())) {
				adminUserManagementDAOImpl.createUser(createdUser);
				modelAndView.addObject("successMessage", INSERT_USER_SUCCESS_MESSAGE);
			} else {
				modelAndView.addObject("errorMessage", DUPLICATE_EMAIL_ERROR_MESSAGE);
			}
		} else {
			modelAndView.addObject("errorMessage", DUPLICATE_USERNAME_ERROR_MESSAGE);
		}
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("adminUserManagement_untitled");
		return modelAndView;
	}
	
	@RequestMapping(value = "/adminUserManagementUpdateUser", method = RequestMethod.POST)
	public ModelAndView handleUpdateUser(@ModelAttribute("user") User_untitled userToUpdate, ModelAndView modelAndView) {		
		adminUserManagementDAOImpl.updateUser(userToUpdate);
		modelAndView.addObject("successMessage", UPDATE_USER_SUCCESS_MESSAGE);
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("adminUserManagement_untitled");
		return modelAndView;
	}
	
	@RequestMapping(value = "/adminUserManagementDeleteUser", method = RequestMethod.POST)
	public ModelAndView handleDeleteUser(@ModelAttribute("user") User_untitled userToDelete, ModelAndView modelAndView) {		
		adminUserManagementDAOImpl.deleteUser(userToDelete);
		modelAndView.addObject("successMessage", DELETE_USER_SUCCESS_MESSAGE);
		List<User_untitled> userList = adminUserManagementDAOImpl.getAllUsers();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("adminUserManagement_untitled");
		return modelAndView;
	}
}
