package com.csis3275.dao_untitled;

import java.util.Optional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.PasswordResetInfo_untitled;

@Component
public class PasswordResetServiceImpl_untitled {
	JdbcTemplate jdbcPasswordReset;
	
	//SQL Queries
	
	@Query("SELECT * FROM user WHERE email = ?")
	public Optional<PasswordResetInfo_untitled> getUserByEmail(String email){
		return getUserByEmail(email);
	}
	
	@Query("SELECT * FROM user WHERE reset_token = ?")
	public Optional<PasswordResetInfo_untitled> getUserByResetToken(String resetToken){
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
