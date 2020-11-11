package com.csis3275.integration_tests_untitled;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.csis3275.dao_untitled.TicketManagementDAOImpl_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.test_config_untitled.TestDatabaseConfig_jpa_66;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 11, 2020
 * TicketIntegrationTest_jpa_66.java
 * com.csis3275.integration_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig_jpa_66.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest(classes = TicketManagementDAOImpl_jpa_66.class)
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test-table-definitions.sql"),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:test-table-rollback.sql")
})
class TicketIntegrationTest_jpa_66 {
	
	@Autowired
	private TicketManagementDAOImpl_jpa_66 ticketDAO;
	
	// Sample Ticket_untitled object
	Ticket_untitled sampleTicket;
	
	// Testing Constants
	private final static int NUMBER_OF_TEST_TICKETS = 5;
	private final static String USERNAME_USER = "user";
	private final static String USERNAME_EMPLOYEE = "employee";
	private final static String ORDER_BY_COLUMN_PRIORITY = "priority";
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		initializeSampleTicket();
	}

	/**
	 * DAO Data Retrieval Test Cases
	 */
	@Test
	void testGetAllTickets() {
		List<Ticket_untitled> tickets = ticketDAO.getAllTickets();
		
		// Check if list is null, empty, and has the correct number of entries
		assertNotNull(tickets);
		assertFalse(tickets.isEmpty());
		assertEquals(tickets.size(), NUMBER_OF_TEST_TICKETS);
	}
	
	@Test
	void testGetTicketByID() {
		Ticket_untitled testTicket = ticketDAO.getTicketByID("1");
		
		// Check if ticket is null and has the correct ID
		assertNotNull(testTicket);
		assertEquals(1, testTicket.getTicketID());
	}
	
	@Test
	void testGetTicketsByOpenedUser() {
		List<Ticket_untitled> tickets = ticketDAO.getTicketsByOpenedUser(USERNAME_USER);
		
		// Check if list is null, empty, and has the correct number of entries
		assertNotNull(tickets);
		assertFalse(tickets.isEmpty());
		assertEquals(tickets.size(), NUMBER_OF_TEST_TICKETS);
		
		// Cycle through tickets and assert each username
		for (Ticket_untitled ticket : tickets) {
			assertEquals(ticket.getUsername(), USERNAME_USER);
		}
	}
	
	@Test
	void testGetTicketsByAssignee() {
		List<Ticket_untitled> tickets = ticketDAO.getTicketsByAssignee(USERNAME_EMPLOYEE);
		
		// Check if list is null, empty, and has the correct number of entries
		assertNotNull(tickets);
		assertFalse(tickets.isEmpty());
		assertEquals(tickets.size(), NUMBER_OF_TEST_TICKETS);
		
		// Cycle through tickets and assert each assignee
		for (Ticket_untitled ticket : tickets) {
			assertEquals(ticket.getAssignee(), USERNAME_EMPLOYEE);
		}
	}
	
	@Test
	void testGetTicketsWithOrderBy() {
		List<Ticket_untitled> tickets = ticketDAO.getTicketsWithOrderBy(ORDER_BY_COLUMN_PRIORITY);
		
		// Check if list is null, empty, and has the correct number of entries
		assertNotNull(tickets);
		assertFalse(tickets.isEmpty());
		assertEquals(tickets.size(), NUMBER_OF_TEST_TICKETS);
		
		// Check if top record has the correct order by value
		assertEquals(tickets.get(0).getPriority(), Ticket_untitled.TICKET_PRIORITY_CRITICAL);
	}
	
	/**
	 * DAO Operation Test Cases
	 */
	@Test
	void testCreateTicket() {
		// Verify successful insert
		assertTrue(ticketDAO.createTicket(sampleTicket));
		
		// Set the sampleTicket ID and compare it with the recently inserted ID
		sampleTicket.setTicketID(NUMBER_OF_TEST_TICKETS+1);
		Ticket_untitled retrievedTicket = ticketDAO.getTicketByID(Integer.toString(sampleTicket.getTicketID()));
		assertEquals(retrievedTicket.getTicketID(), sampleTicket.getTicketID());
		
		// DAO correctly sets the status
		assertEquals(retrievedTicket.getStatus(), Ticket_untitled.TICKET_STATUS_OPEN);
		
		// DAO correctly sets the dateOpened and lastUpdated
		assertNotNull(retrievedTicket.getDateOpened());
		assertNotNull(retrievedTicket.getLastUpdated());
	}
	
	@Test
	void testUpdateTicket() {
		ticketDAO.createTicket(sampleTicket);
		
		// Set the sampleTicket ID and modify priority value on ticket
		sampleTicket.setTicketID(NUMBER_OF_TEST_TICKETS+1);
		sampleTicket.setPriority(Ticket_untitled.TICKET_PRIORITY_HIGH);
		
		// Verify successful update
		assertTrue(ticketDAO.updateTicket(sampleTicket));
		
		// DAO correctly updates priority field
		Ticket_untitled retrievedTicket = ticketDAO.getTicketByID(Integer.toString(sampleTicket.getTicketID()));
		assertEquals(retrievedTicket.getPriority(), Ticket_untitled.TICKET_PRIORITY_HIGH);
	}
	
	@Test
	void testCloseTicket() {
		ticketDAO.createTicket(sampleTicket);

		// Set the sampleTicket ID on ticket
		sampleTicket.setTicketID(NUMBER_OF_TEST_TICKETS+1);
		
		// Verify successful close
		assertTrue(ticketDAO.closeTicket(sampleTicket));
		
		// DAO correctly updates ticket status and date closed
		Ticket_untitled retrievedTicket = ticketDAO.getTicketByID(Integer.toString(sampleTicket.getTicketID()));
		assertEquals(retrievedTicket.getStatus(), Ticket_untitled.TICKET_STATUS_CLOSED);
		assertNotNull(retrievedTicket.getDateClosed());
	}
	
	@Test
	void testDeleteTicket() {
		ticketDAO.createTicket(sampleTicket);

		// Set the sampleTicket ID on ticket
		sampleTicket.setTicketID(NUMBER_OF_TEST_TICKETS+1);

		// Verify successful delete
		assertTrue(ticketDAO.deleteTicket(sampleTicket));
		
		// Try to retrieve non-existent record
		Exception emptyResultDataAccessException = assertThrows(EmptyResultDataAccessException.class, () -> {
			ticketDAO.getTicketByID(Integer.toString(sampleTicket.getTicketID()));
		});
		
		// Check that correct error is returned
		String expectedExceptionMessage = "Incorrect result size: expected 1, actual 0";
		assertEquals(emptyResultDataAccessException.getMessage(), expectedExceptionMessage);
	}
	
	/**
	 * Initializes sampleTicket object for testing
	 */
	public void initializeSampleTicket() {
		sampleTicket = new Ticket_untitled();
		sampleTicket.setTitle("Sample Ticket Title");
		sampleTicket.setDescription("Sample Ticket DescriptionS");
		sampleTicket.setPriority(Ticket_untitled.TICKET_PRIORITY_TRIVIAL);
		sampleTicket.setStatus(Ticket_untitled.TICKET_STATUS_OPEN);
		sampleTicket.setUsername("user");
		sampleTicket.setAssignee("employee");
		sampleTicket.setCategory(Ticket_untitled.TICKET_CATEGORY_GENERAL);
	}
	
	/**
	 * Creates a Timestamp object for the current time, sets the timestamp variable for the sampleTicket tests
	 * @return Timestamp object representing the current time 
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
}
