package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.TicketRowMapper_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;

@Component
public class TicketDisplayDAO_mwi_18 {
	JdbcTemplate jdbcTemplate;
	
	private String SQL_GET_ALL_UNASSIGNED_TICKETS = "SELECT * FROM TICKETS WHERE assignee IS NULL;";
	private String SQL_GET_ALL_EMPLOYEES_TICKETS = "SELECT * FROM TICKETS WHERE assignee = ?;";
	
	@Autowired
	public TicketDisplayDAO_mwi_18(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Ticket_untitled> getAllUnassignedTickets(){
		return jdbcTemplate.query(SQL_GET_ALL_UNASSIGNED_TICKETS, new TicketRowMapper_jpa_66());
	}
	
	public List<Ticket_untitled> getAssignedTickets(String assignee){
		return jdbcTemplate.query(SQL_GET_ALL_EMPLOYEES_TICKETS, new TicketRowMapper_jpa_66(),assignee);
	}
}
