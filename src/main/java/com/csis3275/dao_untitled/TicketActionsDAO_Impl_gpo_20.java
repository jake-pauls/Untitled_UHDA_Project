package com.csis3275.dao_untitled;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
	
	//Tickets Table Queries
	private final String SQL_ASSIGN_TICKET_BY_TIKETID = "UPDATE " + TICKET_TABLE_NAME + " SET assignee = ? WHERE ticketID = ?";
	private final String SQL_CHANGE_TICKET_STATUS_BY_TIKETID = "UPDATE " + TICKET_TABLE_NAME + " SET status = ? WHERE ticketID = ?";
	private final String SQL_CHANGE_TICKET_PRIORITY_BY_TIKETID = "UPDATE " + TICKET_TABLE_NAME + " SET priority = ? WHERE ticketID = ?";
	
	@Autowired
	public TicketActionsDAO_Impl_gpo_20(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}	
	
}
