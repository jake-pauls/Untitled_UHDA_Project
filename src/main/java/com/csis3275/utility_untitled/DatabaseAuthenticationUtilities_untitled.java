package com.csis3275.utility_untitled;

import org.springframework.jdbc.core.JdbcTemplate;

import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 3, 2020
 * DatabaseAuthenticationUtilities_untitled.java
 * com.csis3275.utility_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

public class DatabaseAuthenticationUtilities_untitled {

	private static final String AUTHORITY_TABLE_NAME = "authorities";
	
	// Authority Table Queries
	private static final String SQL_ADMIN_INSERT_USER_AUTHORITY = "INSERT INTO " + AUTHORITY_TABLE_NAME + " (username, authority) VALUES (?,?)";
	private static final String SQL_ADMIN_UPDATE_USER_AUTHORITY = "UPDATE " + AUTHORITY_TABLE_NAME + " SET authority = ? WHERE username = ?";
	private static final String SQL_ADMIN_DELETE_USER_AUTHORITY = "DELETE FROM " + AUTHORITY_TABLE_NAME + " WHERE username = ?";
		
	// Authority Table 'authority' values
	private static final String AUTHORITY_ADMIN = "ROLE_ADMIN";
	private static final String AUTHORITY_EMPLOYEE = "ROLE_EMPLOYEE";
	private static final String AUTHORITY_USER = "ROLE_USER";
	
	/**
	 * Creates a new authority for a given user within the application
	 * @param jdbcTemplate jdbcTemplate with context-specific DataSource
	 * @param user The user requiring authority
	 * @return boolean value determining whether the query successfully added the user authority, if rows returned is greater than 0 the result is true
	 */
	public static boolean createUserAuthority(JdbcTemplate jdbcTemplate, User_untitled user) {
		return jdbcTemplate.update(SQL_ADMIN_INSERT_USER_AUTHORITY, user.getUsername(), checkUserAuthority(user)) > 0;
	}
	
	/**
	 * Updates the particular authority for a given user within the application
	 * @param jdbcTemplate jdbcTemplate with context-specific DataSource
	 * @param user The user requiring authority
	 * @return boolean value determining whether the query successfully updated the user authority, if rows returned is greater than 0 the result is true
	 */
	public static boolean updateUserAuthority(JdbcTemplate jdbcTemplate, User_untitled user) {
		return jdbcTemplate.update(SQL_ADMIN_UPDATE_USER_AUTHORITY, checkUserAuthority(user), user.getUsername()) > 0;
	}
	
	/**
	 * Deletes a particular authority for a given user within the application
	 * @param jdbcTemplate jdbcTemplate with context-specific DataSource
	 * @param user The user requiring authority
	 * @return boolean value determining whether the query successfully deleted the user authority, if rows returned is greater than 0 the result is true
	 */
	public static boolean deleteUserAuthority(JdbcTemplate jdbcTemplate, User_untitled user) {
		return jdbcTemplate.update(SQL_ADMIN_DELETE_USER_AUTHORITY, user.getUsername()) > 0;
	}
	
	/**
	 * Determines an authority level for a particular user object
	 * @param user User object to generate an authority level for
	 * @return String value containing the required authority level
	 */
	private static String checkUserAuthority(User_untitled user) {
		String authority = "";
		if (user.getRole().equals("admin")) {
			authority = AUTHORITY_ADMIN;
		} else if (user.getRole().equals("employee")) {
			authority = AUTHORITY_EMPLOYEE;
		} else {
			authority = AUTHORITY_USER;
		}
		return authority;
	}
	
}
