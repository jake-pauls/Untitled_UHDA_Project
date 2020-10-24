package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.LoginRowMapper_mwi_18;
import com.csis3275.model_untitled.Login_mwi_18;
import com.csis3275.model_untitled.User_untitled;

@Component
public class Login_RegisterDAO_mwi_18 {
	JdbcTemplate jdbcTemplate;
	
	
	private final String SQL_GET_LOGIN_INFO = "SELECT username, password FROM users WHERE username = ? AND password = ?;";
	private final String SQL_CREATE_USER = "INSERT INTO users(username, password, first_name, last_name, email, security_question,"
			+ "security_answer,reset_token,role) VALUES(?,?,?,?,?,?,?,?,?)";
	
	@Autowired
	public Login_RegisterDAO_mwi_18(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List<Login_mwi_18> checkCredentials(Login_mwi_18 user){
		 return jdbcTemplate.query(SQL_GET_LOGIN_INFO, new LoginRowMapper_mwi_18(),user.getUsername(),user.getPassword());
		
	}
	
	public boolean createUser(User_untitled user) {
		return jdbcTemplate.update(SQL_CREATE_USER,user.getUsername(),user.getPassword(),user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getSecurityQ(), user.getSecurityA(),user.getResetToken(), user.getRole()) > 0;
	}
	
	
}
