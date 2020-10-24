package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LoginRowMapper_mwi_18 implements RowMapper<Login_mwi_18>{

	@Override
	public Login_mwi_18 mapRow(ResultSet rs, int rowNum) throws SQLException {
		Login_mwi_18 login = new Login_mwi_18();
		login.setUsername(rs.getString("username"));
		login.setPassword(rs.getString("password"));
		
		return login;
	}

}
