package com.csis3275.model_untitled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csis3275.dao_untitled.PasswordResetDAO_gpo_20;

@Component
public class PasswordReset_gpo_20 {
	@Autowired
	PasswordResetDAO_gpo_20 passwordResetDAO;
	
	private String email;
	private String resetToken;
	
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
	
	
}
