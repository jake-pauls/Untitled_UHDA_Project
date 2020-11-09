package com.csis3275.model_untitled;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 5, 2020
 * TicketRowMapper_jpa_66.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
public class TicketRowMapper_jpa_66 implements RowMapper<Ticket_untitled>{

	@Override
	public Ticket_untitled mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Ticket_untitled ticket = new Ticket_untitled();
		
		ticket.setTicketID(resultSet.getString("ticketId"));
		ticket.setTitle(resultSet.getString("title"));
		ticket.setDescription(resultSet.getString("description"));
		ticket.setPriority(resultSet.getString("priority"));
		ticket.setStatus(resultSet.getString("status"));
		ticket.setUsername(resultSet.getString("username"));
		ticket.setAssignee(resultSet.getString("assignee"));
		ticket.setCategory(resultSet.getString("category"));
		ticket.setDateOpened(resultSet.getTimestamp("dateOpened"));
		ticket.setFormattedDateOpen();
		ticket.setDateClosed(resultSet.getTimestamp("dateClosed"));
		ticket.setLastUpdated(resultSet.getTimestamp("lastUpdated"));
		
		return ticket;
	}
	
}
