package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.UserRowMapper_mwi_18;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Oct 25, 2020
 * AdminUserManagementDAOImpl_untitled.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
public class AdminUserManagementDAOImpl_jpa_66 {
	JdbcTemplate jdbcNewUserTemplate;
	
	// SQL Queries
	private final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
	private final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
	private final String SQL_ADMIN_INSERT_USER = "INSERT INTO users(username, first_name, last_name, email, role) VALUES (?,?,?,?,?)";
	// Updates only permissible on first_name, last_name, email and role
	private final String SQL_ADMIN_UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, email = ?, role = ? WHERE username = ?";
	private final String SQL_ADMIN_DELETE_USER = "DELETE FROM users WHERE username = ?";
	private final String SQL_CHECK_IF_USERNAME_EXISTS = "SELECT * FROM users WHERE username = ?";
	private final String SQL_CHECK_IF_EMAIL_EXISTS = "SELECT * FROM users WHERE email = ?";
	// Authority Table Queries
	private final String SQL_ADMIN_INSERT_USER_AUTHORITY = "INSERT INTO authorities (username, authority) VALUES (?,?)";
	private final String SQL_ADMIN_UPDATE_USER_AUTHORITY = "UPDATE authorities SET authority = ? WHERE username = ?";
	private final String SQL_ADMIN_DELETE_USER_AUTHORITY = "DELETE FROM authorities WHERE username = ?";
	
	@Autowired
	public AdminUserManagementDAOImpl_jpa_66(DataSource dataSource) {
		jdbcNewUserTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Retrieves all users from the database
	 * @return List of 'User_untitled' objects containing each user within the database
	 */
	public List<User_untitled> getAllUsers() {
		return jdbcNewUserTemplate.query(SQL_SELECT_ALL_USERS, new UserRowMapper_mwi_18());
	}
	
	/**
	 * Retrieves individual user through their username in the workspace
	 * @param username String value for the username of the target user being retrieved
	 * @return User_unitled object with values for the target user retrieved
	 */
	public User_untitled getUserByUsername(String username) {
		return jdbcNewUserTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, new Object[]{username}, new UserRowMapper_mwi_18());
	}
	
	/**
	 * Checks if a target username exists in the database
	 * @param username String value for the username being checked
	 * @return boolean value for the query, returns true if username does not exist
	 */
	public boolean checkIfUsernameExists(String username) {
		return jdbcNewUserTemplate.query(SQL_CHECK_IF_USERNAME_EXISTS, new Object[] {username}, new UserRowMapper_mwi_18()).isEmpty();
	}
	
	/**
	 * Checks if a target email exists in the database
	 * @param email String value for the email being checked
	 * @return boolean value for the query, returns true if email does not exist
	 */
	public boolean checkIfEmailExists(String email) {
		return jdbcNewUserTemplate.query(SQL_CHECK_IF_EMAIL_EXISTS, new Object[] {email}, new UserRowMapper_mwi_18()).isEmpty();
	}
	
	/**
	 * Operation to create a user and add it to the database, whenever a user is added an authority must also be added
	 * @param user The 'User_untitled' object for the user being added
	 * @return boolean value determining whether the query successfully added the user authority, if rows returned is greater than 0 the result is true
	 */
	public boolean createUser(User_untitled user) {
		jdbcNewUserTemplate.update(SQL_ADMIN_INSERT_USER, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
		return createUserAuthority(user, checkUserAuthority(user.getRole()));
	}
	
	/**
	 * Operation to update a user in the database
	 * @param user The 'User_untitled' object for the user being updated
	 * @return boolean value determining whether the query successfully updated the user, if rows returned is greater than 0 the result is true
	 */
	public boolean updateUser(User_untitled user) {
		// Update the user's authority level if necessary
		updateUserAuthority(user, checkUserAuthority(user.getRole()));
		return jdbcNewUserTemplate.update(SQL_ADMIN_UPDATE_USER, user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole(), user.getUsername()) > 0;
	}
	
	/**
	 * Operation to delete a user in the database
	 * @param user The 'User_untitled' object for the user being deleted
	 * @return boolean value determining whether the query successfully deleted the user, if rows returned is greater than 0 the result is true
	 */
	public boolean deleteUser(User_untitled user) {
		deleteUserAuthority(user);
		return jdbcNewUserTemplate.update(SQL_ADMIN_DELETE_USER, user.getUsername()) > 0;
	}
	
	private boolean createUserAuthority(User_untitled user, String authority) {
		return jdbcNewUserTemplate.update(SQL_ADMIN_INSERT_USER_AUTHORITY, user.getUsername(), authority) > 0;
	}
	
	private boolean updateUserAuthority(User_untitled user, String authority) {
		return jdbcNewUserTemplate.update(SQL_ADMIN_UPDATE_USER_AUTHORITY, authority, user.getUsername()) > 0;
	}
	
	private boolean deleteUserAuthority(User_untitled user) {
		return jdbcNewUserTemplate.update(SQL_ADMIN_DELETE_USER_AUTHORITY, user.getUsername()) > 0;
	}
	
	private String checkUserAuthority(String authority) {
		if (authority.equals("admin")) {
			authority = "ROLE_ADMIN";
		} else if (authority.equals("employee")) {
			authority = "ROLE_EMPLOYEE";
		} else {
			authority = "ROLE_USER";
		}
		return authority;
	}
}
