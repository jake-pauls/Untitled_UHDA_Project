package com.csis3275.model_untitled;



import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.dao_untitled.Login_RegisterDAO_mwi_18;

public class Login_mwi_18 {
	@Autowired
	Login_RegisterDAO_mwi_18 dao;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
	
}
