package com.csis3275.model_untitled;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csis3275.dao_untitled.Login_RegisterDAO_mwi_18;

/**
 * 
 * @author Michael Wilson 300278118
 * @date 
 * Login_mwi_18.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */
@Component
public class Login_mwi_18 {
	@Autowired
	Login_RegisterDAO_mwi_18 dao;
	
	private String username;
	private String password;
	
	public Login_mwi_18() {
		
	}
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
