package com.csis3275.utility_untitled;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csis3275.dao_untitled.AdminUserManagementDAOImpl_jpa_66;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 5, 2020
 * UserAuthenticationUtilities_untitled.java
 * com.csis3275.utility_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
public class UserAuthenticationUtilities_untitled {

	@Autowired
	AdminUserManagementDAOImpl_jpa_66 authenticatedUserDAO;
	
	/**
	 * Retrieves currently logged in user context based on a given authenticated user principal
	 * @param principal The principal object associated with an authenticated user
	 * @return 'User_untitled' object for the currently authenticated user
	 */
	public User_untitled getLoggedInUserContext(Principal principal) {
		return authenticatedUserDAO.getUserByUsername(principal.getName());
	}
	
}
