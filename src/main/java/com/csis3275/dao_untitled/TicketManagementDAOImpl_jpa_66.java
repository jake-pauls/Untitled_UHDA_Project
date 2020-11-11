package com.csis3275.dao_untitled;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.TicketRowMapper_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 5, 2020
 * TicketManagementDAOImpl_jpa_66.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Component
@Primary
public class TicketManagementDAOImpl_jpa_66 {
	JdbcTemplate jdbcTicketManagementTemplate;
	
	private final String TICKET_TABLE_NAME = "tickets";	
	
	// Tickets Table Queries
	private final String SQL_SELECT_ALL_TICKETS = "SELECT * FROM " + TICKET_TABLE_NAME;
	private final String SQL_SELECT_TICKETS_BY_OPENED_USER = "SELECT * FROM " + TICKET_TABLE_NAME + " WHERE username = ?";
	private final String SQL_SELECT_TICKETS_BY_ASSIGNEE = "SELECT * FROM " + TICKET_TABLE_NAME + " WHERE assignee = ?";
	private final String SQL_SELECT_TICKETS_WITH_ORDER_BY = "SELECT * FROM " + TICKET_TABLE_NAME + " ORDER BY %s";
	private final String SQL_SELECT_TICKET_BY_ID = "SELECT * FROM " + TICKET_TABLE_NAME + " WHERE ticketID = ?";
	private final String SQL_INSERT_NEW_TICKET = "INSERT INTO " + TICKET_TABLE_NAME + 
												 " (title, description, priority, status, username, assignee, category, dateOpened, lastUpdated)" +
												 " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_UPDATE_TICKET = "UPDATE " + TICKET_TABLE_NAME + " SET title = ?, description = ?, priority = ?, status = ?, username = ?, assignee = ?, category = ?, dateClosed = ?, lastUpdated = ? WHERE ticketID = ?";
	private final String SQL_CLOSE_TICKET = "UPDATE " + TICKET_TABLE_NAME + " SET dateClosed = ? WHERE ticketID = ?";
	private final String SQL_DELETE_TICKET = "DELETE FROM " + TICKET_TABLE_NAME + " WHERE ticketID = ?";
	
	@Autowired
	public TicketManagementDAOImpl_jpa_66(DataSource dataSource) {
		jdbcTicketManagementTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Retrieves all tickets from the database
	 * @return List of 'Ticket_untitled' objects containing each ticket within the database
	 */
	public List<Ticket_untitled> getAllTickets() {
		return jdbcTicketManagementTemplate.query(SQL_SELECT_ALL_TICKETS, new TicketRowMapper_jpa_66());
	}
	
	/**
	 * Retrieves all tickets from the database based on the opened user
	 * @param openedUserUsername String username for the desired user to retrieve tickets from
	 * @return List of 'Ticket_untitled' objects containing each of the user's tickets within the database
	 */
	public List<Ticket_untitled> getTicketsByOpenedUser(String openedUserUsername) {
		return jdbcTicketManagementTemplate.query(SQL_SELECT_TICKETS_BY_OPENED_USER, new Object[] {openedUserUsername}, new TicketRowMapper_jpa_66());
	}

	/**
	 * Retrieves all tickets from the database based on the assignee
	 * @param assigneeUsername String username for the desired assignee to retrieve tickets from
	 * @return List of 'Ticket_untitled' objects containing each of the assignee's tickets within the database
	 */
	public List<Ticket_untitled> getTicketsByAssignee(String assigneeUsername) {
		return jdbcTicketManagementTemplate.query(SQL_SELECT_TICKETS_BY_ASSIGNEE, new Object[] {assigneeUsername}, new TicketRowMapper_jpa_66());
	}
	
	/**
	 * Retrieves all tickets from the database with a particular column to order by
	 * @param orderByColumn The name of the column to order the results by
	 * @return A List of 'Ticket_untitled' objects containing each of the tickets within the ordered list
	 */
	public List<Ticket_untitled> getTicketsWithOrderBy(String orderByColumn) {
		String formattedQuery = String.format(SQL_SELECT_TICKETS_WITH_ORDER_BY, orderByColumn);
		return jdbcTicketManagementTemplate.query(formattedQuery, new TicketRowMapper_jpa_66());
	}
	
	/**
	 * Retrieves a particular ticket based on its ticketID
	 * @param ticketID String containing the ID for the desired ticket to retrieve
	 * @return A 'Ticket_untitled' object bound to the ticket with the desired ticketID
	 */
	public Ticket_untitled getTicketByID(String ticketID) {
		return jdbcTicketManagementTemplate.queryForObject(SQL_SELECT_TICKET_BY_ID, new Object[] {ticketID}, new TicketRowMapper_jpa_66());
	}
	
	/**
	 * Operation to create a ticket and add it to the database, performs supplementary actions to support base ticket creation
	 * @param ticket The 'Ticket_untitled' object for the ticket being added
	 * @return boolean value determining whether the query successfully added the user authority, if rows returned is greater than 0 the result is true
	 */
	public boolean createTicket(Ticket_untitled ticket) {
		// All newly created tickets will be initailized with the status 'Open'
		ticket.setStatus(Ticket_untitled.TICKET_STATUS_OPEN);
		// Set a DateOpened Timestamp and update the lastUpdated Timestamp
		ticket.setDateOpened(getCurrentTime());
		ticket.setLastUpdated(getCurrentTime());
		return jdbcTicketManagementTemplate.update(SQL_INSERT_NEW_TICKET, ticket.getTitle(), ticket.getDescription(), 
																		  ticket.getPriority(), ticket.getStatus(), 
																		  ticket.getUsername(), ticket.getAssignee(), 
																		  ticket.getCategory(), ticket.getDateOpened(), 
																		  ticket.getLastUpdated()) > 0;
	}
	
	/**
	 * Operation to update a ticket in the database
	 * @param ticket The 'Ticket_untitled' object for the ticket being updated
	 * @return boolean value determining whether the query successfully updated the user, if rows returned is greater than 0 the result is true
	 */
	public boolean updateTicket(Ticket_untitled ticket) {
		// Update the lastUpdated Timestamp
		ticket.setLastUpdated(getCurrentTime());
		return jdbcTicketManagementTemplate.update(SQL_UPDATE_TICKET, ticket.getTitle(), ticket.getDescription(), 
																	  ticket.getPriority(), ticket.getStatus(), 
																	  ticket.getUsername(), ticket.getAssignee(), 
																	  ticket.getCategory(), ticket.getDateClosed(), 
																	  ticket.getLastUpdated(), ticket.getTicketID()) > 0;
	}
	
	/**
	 * Operation to close a ticket in the database
	 * @param ticket The 'Ticket_untitled' object for the ticket being closed
	 * @return boolean value determining whether the query successfully updated the user, if rows returned is greater than 0 the result is true
	 */
	public boolean closeTicket(Ticket_untitled ticket) {
		// All newly closed tickets will have a status set to 'Closed'
		ticket.setStatus(Ticket_untitled.TICKET_STATUS_CLOSED);
		// Set a DateClosed Timestamp and update the lastUpdated Timestamp
		ticket.setDateClosed(getCurrentTime());
		ticket.setLastUpdated(getCurrentTime());
		return jdbcTicketManagementTemplate.update(SQL_CLOSE_TICKET, ticket.getDateClosed(), ticket.getTicketID()) > 0;
	}
	
	/**
	 * Operation to delete a ticket in the database
	 * @param ticket The 'Ticket_untitled' object for the user being deleted
	 * @return boolean value determining whether the query successfully deleted the user, if rows returned is greater than 0 the result is true
	 */
	public boolean deleteTicket(Ticket_untitled ticket) {
		return jdbcTicketManagementTemplate.update(SQL_DELETE_TICKET, ticket.getTicketID()) > 0;
	}
	
	/**
	 * Creates a Timestamp object for the current time
	 * @return Timestamp object representing the current time 
	 */
	public Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
}
