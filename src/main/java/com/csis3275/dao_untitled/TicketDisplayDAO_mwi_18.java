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
	
	private String SQL_GET_ALL_UNASSIGNED_TICKETS = "SELECT * FROM TICKETS WHERE assignee IS NULL ORDER BY %s;";
	private String SQL_GET_ALL_EMPLOYEES_TICKETS = "SELECT * FROM TICKETS WHERE assignee = ? ORDER BY %s;";
	private String SQL_GET_LIST_OF_EMPLOYEES_AND_ADMINS = "SELECT * FROM USERS WHERE role = 'admin' OR role = 'employee';";
	private String SQL_GET_CREATED_TICKETS = "SELECT * FROM TICKETS WHERE username = ? ORDER BY %s;";
	private String SQL_ASSIGN_TICKET = "UPDATE tickets SET assignee = ? WHERE ticketID = ?;";
	private String SQL_GET_ONE_TICKET = "SELECT * FROM tickets WHERE ticketid = ?;";
	
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
		String formattedSQL = String.format(SQL_GET_ALL_EMPLOYEES_TICKETS, order);
		
		return jdbcTemplate.query(formattedSQL, new TicketRowMapper_jpa_66(),assignee);
	}
	
	/**
	 * Gets a list of tickets that a specified user has made
	 * @param assignee
	 * @param order
	 * @return list of created tickets
	 */
	public List<Ticket_untitled> getCreatedTickets(String assignee,String order){
		String formattedSQL = String.format(SQL_GET_CREATED_TICKETS, order);
		
		return jdbcTemplate.query(formattedSQL, new TicketRowMapper_jpa_66(),assignee);
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
