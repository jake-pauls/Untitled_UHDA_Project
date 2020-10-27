package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.UserRowMapper_mwi_18;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls
 * @date Oct 25, 2020
 * Untitled_UHDA_Project
 */

@Component
public class AdminUserManagementDAOImpl_untitled {
	JdbcTemplate jdbcNewUserTemplate;
	
	// SQL Queries
	private final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
	private final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
	private final String SQL_SELECT_USER_BY_ROLE = "SELECT * FROM users WHERE role = ?";
	private final String SQL_ADMIN_INSERT_USER = "INSERT INTO users(username, first_name, last_name, email, role) VALUES (?,?,?,?,?)";
	// Updates only permissible on first_name, last_name, and role
	private final String SQL_ADMIN_UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, email = ?, role = ? WHERE username = ?";
	private final String SQL_ADMIN_DELETE_USER = "DELETE FROM users WHERE username = ?";
	private final String SQL_CHECK_IF_USERNAME_EXISTS = "SELECT * FROM users WHERE username = ?";
	private final String SQL_CHECK_IF_EMAIL_EXISTS = "SELECT * FROM users WHERE email = ?";
	
	
	@Autowired
	public AdminUserManagementDAOImpl_untitled(DataSource dataSource) {
		jdbcNewUserTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<User_untitled> getAllUsers() {
		return jdbcNewUserTemplate.query(SQL_SELECT_ALL_USERS, new UserRowMapper_mwi_18());
	}
	
	public User_untitled getUserByUsername(String username) {
		return jdbcNewUserTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, new Object[]{username}, new UserRowMapper_mwi_18());
	}
	
	//consider deleting?
	public User_untitled getUserByRole(String role) {
		return jdbcNewUserTemplate.queryForObject(SQL_SELECT_USER_BY_ROLE, new Object[]{role}, new UserRowMapper_mwi_18());
	}
	
	public boolean checkIfUsernameExists(String username) {
		return jdbcNewUserTemplate.query(SQL_CHECK_IF_USERNAME_EXISTS, new Object[] {username}, new UserRowMapper_mwi_18()).isEmpty();
	}
	
	public boolean checkIfEmailExists(String email) {
		return jdbcNewUserTemplate.query(SQL_CHECK_IF_EMAIL_EXISTS, new Object[] {email}, new UserRowMapper_mwi_18()).isEmpty();
	}
	
	public boolean createUser(User_untitled user) {
		return jdbcNewUserTemplate.update(SQL_ADMIN_INSERT_USER, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole()) > 0;
	}
	
	public boolean updateUser(User_untitled user) {
		return jdbcNewUserTemplate.update(SQL_ADMIN_UPDATE_USER, user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole(), user.getUsername()) > 0;
	}
	
	public boolean deleteUser(User_untitled user) {
		return jdbcNewUserTemplate.update(SQL_ADMIN_DELETE_USER, user.getUsername()) > 0;
	}
}
