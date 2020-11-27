package com.csis3275.dao_untitled;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 26, 2020
 * SlackAssociationDAOImpl_jpa_66.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
public class SlackAssociationDAOImpl_jpa_66 {
	JdbcTemplate jdbcSlackAssociationTemplate;
	
	private final String SLACK_ASSOCIATION_TABLE_NAME = "slack_association";
	
	// Slack Association Table Queries
	private final String SQL_SELECT_ASSOCIATION_BY_USER_EMAIL = "SELECT * FROM " + SLACK_ASSOCIATION_TABLE_NAME + " WHERE email = ? AND slackUserId IS NOT NULL";
	private final String SQL_ADD_SLACK_ASSOCIATION = "INSERT INTO " + SLACK_ASSOCIATION_TABLE_NAME + " (email, slackUserId) VALUES (?,?)";
	
	@Autowired
	public SlackAssociationDAOImpl_jpa_66(DataSource dataSource) {
		jdbcSlackAssociationTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean checkUserSlackAssociation(String email) {
		return !jdbcSlackAssociationTemplate.queryForList(SQL_SELECT_ASSOCIATION_BY_USER_EMAIL, new Object[] {email}).isEmpty();
	}
	
	public boolean createSlackAssociation(String email, String slackUserId) {
		return jdbcSlackAssociationTemplate.update(SQL_ADD_SLACK_ASSOCIATION, email, slackUserId) > 0;
	}
}
