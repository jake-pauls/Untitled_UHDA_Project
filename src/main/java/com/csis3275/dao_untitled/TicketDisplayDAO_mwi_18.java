package com.csis3275.dao_untitled;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.csis3275.model_untitled.TicketRowMapper_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.UserRowMapper_mwi_18;
import com.csis3275.model_untitled.User_untitled;

/**
 * 
 * @author Michael Wilson 300278118
 * @date Nov. 3, 2020
 * TicketDisplayDAO_mwi_18.java
 * com.csis3275.dao_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */
@Component
public class TicketDisplayDAO_mwi_18 {
	JdbcTemplate jdbcTemplate;
	
	private String priorityCaseStatement = " CASE priority " + 
			"WHEN 'Critical' THEN 1 " + 
			"WHEN 'High' THEN 2 " +  
			"WHEN 'Normal' THEN 3 " + 
			"WHEN 'Low' THEN 4 " + 
			"WHEN 'Trivial' THEN 5 " + 
			"END";
	
	private String statusCaseStatement = " CASE status " +
			"WHEN 'Open' THEN 1 " +
			"WHEN 'In Progress' THEN 2 " +
			"WHEN 'Resolved' THEN 3 " +
			"WHEN 'Closed' THEN 4 " +
			"END";
	
	private String SQL_GET_ALL_UNASSIGNED_TICKETS = "SELECT * FROM TICKETS WHERE assignee IS NULL ORDER BY %s;";
	private String SQL_GET_ALL_EMPLOYEES_TICKETS = "SELECT * FROM TICKETS WHERE assignee = ? ORDER BY %s;";
	private String SQL_GET_LIST_OF_EMPLOYEES_AND_ADMINS = "SELECT * FROM USERS WHERE role = 'admin' OR role = 'employee';";
	private String SQL_GET_CREATED_TICKETS = "SELECT * FROM TICKETS WHERE username = ? ORDER BY %s;";
	private String SQL_ASSIGN_TICKET = "UPDATE tickets SET assignee = ? WHERE ticketID = ?;";
	private String SQL_GET_ONE_TICKET = "SELECT * FROM tickets WHERE ticketid = ?;";
	private String SQL_GET_TOP_PRIORTY_TICKETS = "SELECT * FROM tickets WHERE assignee = ? ORDER BY " + priorityCaseStatement + " LIMIT 6;";
	private String SQL_GET_MOST_RECENT_UNASSIGNED_TICKETS = "SELECT * FROM tickets WHERE assignee IS NULL ORDER BY dateOpened DESC LIMIT 6;";
	private String SQL_GET_MOST_RECENT_CREATED_TICKETS = "SELECT * FROM tickets WHERE username = ? ORDER BY dateOpened DESC LIMIT 6";
	private String SQL_GET_CREATED_TICKETS_PRIORITY_SORT = "SELECT * FROM tickets WHERE username = ? ORDER BY " + priorityCaseStatement + ";";
	private String SQL_GET_ALL_EMPLOYEES_TICKETS_PRIORITY_SORT = "SELECT * FROM tickets WHERE assignee = ? ORDER BY " + priorityCaseStatement + ";";
	private String SQL_GET_ALL_CREATED_TICKETS_STATUS_SORT = "SELECT * FROM tickets WHERE username = ? ORDER BY " + statusCaseStatement + ";";
	private String SQL_GET_ALL_EMPLOYEES_TICKETS_STATUS_SORT = "SELECT * FROM tickets WHERE assignee = ? ORDER BY " + statusCaseStatement + ";";
	
	/**
	 * Initializes the jdbc template
	 * @param dataSource
	 */
	@Autowired
	public TicketDisplayDAO_mwi_18(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Gets all unassigned tickets from the db
	 * @param order 	This is the order for the sql when getting tickets
	 * @return 			list of unassigned tickets
	 */
	public List<Ticket_untitled> getAllUnassignedTickets(String order){
		String formattedSQL = String.format(SQL_GET_ALL_UNASSIGNED_TICKETS, order);
		
		return jdbcTemplate.query(formattedSQL, new TicketRowMapper_jpa_66());
	}
	
	/**
	 * Gets all assigned tickets for an employee
	 * @param assignee
	 * @param order		This is the order for the sql when getting tickets
	 * @return 			list of assigned tickets
	 */
	public List<Ticket_untitled> getAssignedTickets(String assignee,String order){
		String formattedSQL = "";
		
		// If order is by priority, include case statement for proper sort
		if (order.equals("priority")) {
			formattedSQL = SQL_GET_ALL_EMPLOYEES_TICKETS_PRIORITY_SORT;
		} else if (order.equals("status")) {
			formattedSQL = SQL_GET_ALL_EMPLOYEES_TICKETS_STATUS_SORT;
		} else {
			formattedSQL = String.format(SQL_GET_ALL_EMPLOYEES_TICKETS, order);
		}
		
		return jdbcTemplate.query(formattedSQL, new TicketRowMapper_jpa_66(),assignee);
	}
	
	/**
	 * Gets a list of tickets that a specified user has made
	 * @param assignee
	 * @param order
	 * @return list of created tickets
	 */
	public List<Ticket_untitled> getCreatedTickets(String assignee,String order){
		String formattedSQL = "";
		
		// If order is by priority, include case statement for proper sort
		if (order.equals("priority")) {
			formattedSQL = SQL_GET_CREATED_TICKETS_PRIORITY_SORT;
		} else if (order.equals("status")) {
			formattedSQL = SQL_GET_ALL_CREATED_TICKETS_STATUS_SORT;
		} else  {
			formattedSQL = String.format(SQL_GET_CREATED_TICKETS, order);
		}
		
		return jdbcTemplate.query(formattedSQL, new TicketRowMapper_jpa_66(),assignee);
	}
	
	/**
	 * Gets a list of that highest priority tickets for a particular user
	 * @param author
	 * @return
	 */
	public List<Ticket_untitled> getTopPriorityTickets(String author) {
		return jdbcTemplate.query(SQL_GET_TOP_PRIORTY_TICKETS,  new TicketRowMapper_jpa_66(), author);
	}
	
	/**
	 * Gets a list of the most recent tickets opened for a particular user
	 * @return
	 */
	public List<Ticket_untitled> getMostRecentTickets(String username) {
		return jdbcTemplate.query(SQL_GET_MOST_RECENT_CREATED_TICKETS,  new TicketRowMapper_jpa_66(), username);
	}
	
	/**
	 * Gets a list of the most recent unassigned tickets
	 * @return
	 */
	public List<Ticket_untitled> getMostRecentUnassignedTickets() {
		return jdbcTemplate.query(SQL_GET_MOST_RECENT_UNASSIGNED_TICKETS,  new TicketRowMapper_jpa_66());
	}
	
	/**
	 * Get a ticket from the database at the specified id
	 * @param id
	 * @return ticket from database
	 */
	public Ticket_untitled getOneTicket(int id){
		List<Ticket_untitled> myList = jdbcTemplate.query(SQL_GET_ONE_TICKET, new TicketRowMapper_jpa_66(),id);
		
		
		return myList.get(0);
	}
	
	/**
	 * Used to assign the ticket to however picked it up
	 * @param id		ticket id
	 * @param username	employee who picked up the ticket
	 * @return 			true if ticket pick up was successful
	 */
	public Boolean pickUpTicket(int id, String username) {
		
		return  jdbcTemplate.update(SQL_ASSIGN_TICKET,username,id) > 0;
		
	}
	
	/**
	 * 
	 * @return list of all employees and admins
	 */
	public List<User_untitled> getListOfEmployeesAndAdmins(){
		
		return jdbcTemplate.query(SQL_GET_LIST_OF_EMPLOYEES_AND_ADMINS, new UserRowMapper_mwi_18());
	}
}
