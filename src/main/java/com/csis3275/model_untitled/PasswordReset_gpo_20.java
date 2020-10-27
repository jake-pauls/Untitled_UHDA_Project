package com.csis3275.model_untitled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;

/**
 * @author Gregory Pohlod
 * @date 10/24/2020
 * PasswordReset_gpo_20.java
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

//getters and seters used in the password reset/ forgot password process in order to set, and get emails, passwords and reset tokens.
@Component
public class PasswordReset_gpo_20 {
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	
	private String email;
	private String resetToken;
	private String password;
	
	public PasswordReset_gpo_20() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
