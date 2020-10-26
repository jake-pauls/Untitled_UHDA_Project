package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper_untitled implements RowMapper<PasswordResetInfo_untitled> {

	public PasswordResetInfo_untitled mapRow(ResultSet resultSet, int i) throws SQLException {
		PasswordResetInfo_untitled currentUser = new PasswordResetInfo_untitled();
		currentUser.setUsername(resultSet.getString("username"));
		currentUser.setEmail(resultSet.getString("email"));
		currentUser.setFirstName(resultSet.getString("first_name"));
		currentUser.setLastName(resultSet.getString("last_name"));
		currentUser.setSecurityQuestion(resultSet.getString("security_question"));
		currentUser.setSecurityAnswer(resultSet.getString("security_answer"));
		currentUser.setResetToken(resultSet.getString("reset_token"));
		currentUser.setRole(resultSet.getString("role"));
		return currentUser;
	}

}
