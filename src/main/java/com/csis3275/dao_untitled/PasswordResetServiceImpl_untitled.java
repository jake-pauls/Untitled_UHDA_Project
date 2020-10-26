package com.csis3275.dao_untitled;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.PasswordResetInfo_untitled;
import com.csis3275.model_untitled.UserMapper_untitled;

@Component
public class PasswordResetServiceImpl_untitled {
	JdbcTemplate jdbcPasswordReset;
	
	//SQL Queries
	private final String SQL_GET_BY_EMAIL = "SELECT * FROM user WHERE email = :email";
	
	@Autowired
	public PasswordResetServiceImpl_untitled(DataSource dataSource) {
		jdbcPasswordReset = new JdbcTemplate(dataSource);
	}
	public PasswordResetInfo_untitled getUserByEmail(String email) {
		return jdbcPasswordReset.queryForObject(SQL_GET_BY_EMAIL, new Object[] {email}, new UserMapper_untitled());
	}
	
	
	
	//@Query("SELECT * FROM user WHERE email = ?")
	//public Optional<PasswordResetInfo_untitled> getUserByEmail(@Param("email") String email){
	//		return getUserByEmail(email);
	//}
	
	@Query("SELECT * FROM user WHERE reset_token = :token")
	public Optional<PasswordResetInfo_untitled> getUserByResetToken(@Param("reset_token") String resetToken){
		return getUserByResetToken(resetToken);
	}
	
	@Modifying
	@Query("UPDATE USERS SET reset_token=?1 WHERE email=?2")
	public void saveResetToken(String resetToken, String email) {
		
	}
	
	@Modifying
	@Query("UPDATE USERS SET password=?1 WHERE reset_token=?2")
	public void savePassword(String password, String resetToken) {
		
	}
	
}
