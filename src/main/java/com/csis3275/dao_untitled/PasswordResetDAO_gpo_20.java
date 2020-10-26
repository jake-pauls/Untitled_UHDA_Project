package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.UserRowMapper_mwi_18;

import com.csis3275.model_untitled.User_untitled;

@Component
public class PasswordResetDAO_gpo_20 {
	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
	private final String SQL_GET_USER_BY_RESET_TOKEN = "SELECT * FROM users WHERE reset_token = ?;";
	

	@Autowired
	public PasswordResetDAO_gpo_20(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User_untitled checkUserEmailExists(String email) {
		List<User_untitled> list = jdbcTemplate.query(SQL_GET_USER_BY_EMAIL, new UserRowMapper_mwi_18(), email);
		try {
			return list.get(0);
		} catch (Exception ex) {
			return null;
		}
	}

}
