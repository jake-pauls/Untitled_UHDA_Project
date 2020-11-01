package com.csis3275.dao_untitled;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TicketDisplayDAO_mwi_18 {
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public TicketDisplayDAO_mwi_18(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
