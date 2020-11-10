package com.csis3275.dao_untitled;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.UserRowMapper_mwi_18;
import com.csis3275.model_untitled.User_untitled;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Nov 7, 2020
 * TicketActionsDAO_Impl_gpo_20.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 *
 */

@Component
public class TicketActionsDAO_Impl_gpo_20 extends TicketManagementDAOImpl_jpa_66 {
	
	private final String TICKET_TABLE_NAME = "tickets";	
	private final String USERS_TABLE_NAME = "users";	
	
	//Tickets Table Queries
	private final String SQL_ASSIGN_TICKET_BY_TIKETID = "UPDATE " + TICKET_TABLE_NAME + " SET assignee = ?, lastupdated = ? WHERE ticketID = ?";
	private final String SQL_CHANGE_TICKET_STATUS_BY_TIKETID = "UPDATE " + TICKET_TABLE_NAME + " SET status = ?, lastupdated = ? WHERE ticketID = ?";
	private final String SQL_CHANGE_TICKET_PRIORITY_BY_TIKETID = "UPDATE " + TICKET_TABLE_NAME + " SET priority = ?, lastupdated = ? WHERE ticketID = ?";
	private final String SQL_GET_USER_PROFILES_FROM_TICKETS = "SELECT * FROM " + USERS_TABLE_NAME +  " WHERE username = ?";
	
	@Autowired
	public TicketActionsDAO_Impl_gpo_20(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	public boolean assignTicket(Ticket_untitled ticket) {
		// Update the lastUpdated Timestamp
		ticket.setLastUpdated(getCurrentTime());
		return jdbcTicketManagementTemplate.update(SQL_ASSIGN_TICKET_BY_TIKETID, ticket.getAssignee(), ticket.getLastUpdated(),ticket.getTicketID()) > 0;
	}
	public boolean changeTicketStatus(Ticket_untitled ticket) {
		// Update the lastUpdated Timestamp
		ticket.setLastUpdated(getCurrentTime());
		return jdbcTicketManagementTemplate.update(SQL_CHANGE_TICKET_STATUS_BY_TIKETID, ticket.getStatus(), ticket.getLastUpdated(), ticket.getTicketID()) > 0;
	}
	public boolean changeTicketPriority(Ticket_untitled ticket) {
		// Update the lastUpdated Timestamp
		ticket.setLastUpdated(getCurrentTime());
		return jdbcTicketManagementTemplate.update(SQL_CHANGE_TICKET_PRIORITY_BY_TIKETID, ticket.getPriority(), ticket.getLastUpdated(), ticket.getTicketID()) > 0;
	}
	public User_untitled getUserProfileByUsername(String username) {
		return jdbcTicketManagementTemplate.queryForObject(SQL_GET_USER_PROFILES_FROM_TICKETS, new Object[]{username}, new UserRowMapper_mwi_18());
	}
	public User_untitled getAssigneeProfileByUsername(String username) {
		return jdbcTicketManagementTemplate.queryForObject(SQL_GET_USER_PROFILES_FROM_TICKETS, new Object[]{username}, new UserRowMapper_mwi_18());
	}
	
}
