package com.csis3275.model_untitled;

import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.dao_untitled.Login_RegisterDAO_mwi_18;

public class Register_mwi_18 {
	@Autowired
	Login_RegisterDAO_mwi_18 dao;
	
	private User_untitled user;

	public User_untitled getUser() {
		return user;
	}

	public void setUser(User_untitled user) {
		this.user = user;
	}
	
	public boolean register() {
		return dao.createUser(this.user);
	}
}
