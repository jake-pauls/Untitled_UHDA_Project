package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.UserRowMapper_mwi_18;

import com.csis3275.model_untitled.User_untitled;

@Component
public class Login_RegisterDAO_mwi_18 {
	JdbcTemplate jdbcTemplate;
	
	
	private final String SQL_GET_LOGIN_INFO = "SELECT * FROM users WHERE username = ? AND password = ?;";
	private final String SQL_CREATE_USER = "INSERT INTO users(username, password, first_name, last_name, email, security_question,"
			+ "security_answer,reset_token,role) VALUES(?,?,?,?,?,?,?,?,?);";
	private final String SQL_CHECK_EMAIL = "SELECT * FROM USERS WHERE email = ?";
	
	@Autowired
	public Login_RegisterDAO_mwi_18(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public User_untitled checkCredentials(String username, String password){
		 List<User_untitled> list = jdbcTemplate.query(SQL_GET_LOGIN_INFO, new UserRowMapper_mwi_18(),username,password);
		try {
			return list.get(0);
		}catch(Exception ex){
			return null;
		}
	}
	
	public int createUser(User_untitled user) {
		try {
			jdbcTemplate.update(SQL_CREATE_USER,user.getUsername(),user.getPassword(),user.getFirstName(),
					user.getLastName(), user.getEmail(), user.getSecurityQ(), user.getSecurityA(),user.getResetToken(), user.getRole());
			
			return 1;
		}catch(DuplicateKeyException ex) {
			return -1;
		}
	}
	
	public boolean checkEmail(String email) {
		try {
			List<User_untitled> list = jdbcTemplate.query(SQL_CHECK_EMAIL,new UserRowMapper_mwi_18(),email);
			if(!list.isEmpty()) {
				return true;
			}else {
				return false;
			}
				
		}catch (Exception ex) {
			return false;
		}
		
	}
	
	
}
