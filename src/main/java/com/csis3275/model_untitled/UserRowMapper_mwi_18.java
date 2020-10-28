package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Michael Wilson 300278118
 * @date 
 * UserRowMapper_mwi_18.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */
@Component
public class UserRowMapper_mwi_18 implements RowMapper<User_untitled>{

	@Override
	public User_untitled mapRow(ResultSet rs, int rowNum) throws SQLException {
		User_untitled user = new User_untitled();
		
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setSecurityQ(rs.getString("security_question"));
		user.setSecurityA(rs.getString("security_answer"));
		user.setResetToken(rs.getString("reset_token"));
		user.setRole(rs.getString("role"));
		
		return user;
	}

}
