package com.csis3275.unit_tests_untitled;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.csis3275.model_untitled.Ticket_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 11, 2020
 * TicketUnitTest_jpa_66.java
 * com.csis3275.unit_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

class TicketUnitTest_jpa_66 {

	// Sample Ticket_untitled object
	Ticket_untitled sampleTicket;
	
	// Timestamp for dateOpened time set for sampleTicket
	Timestamp sampleTicketDateOpenedTimestamp;
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		initializeSampleTicket();
	}
	
	/**
	 * General Attribute Tests
	 */
	@Test
	void getTicketID() {
		assertEquals(1, sampleTicket.getTicketID());
	}
	
	@Test
	void getTitle() {
		assertEquals("Sample Ticket Title #1", sampleTicket.getTitle());
	}
	
	@Test
	void getDescription() {
		assertEquals("Sample Ticket Description #1", sampleTicket.getDescription());
	}
	
	@Test
	void getPriority() {
		assertEquals("Trivial", sampleTicket.getPriority());
	}
	
	@Test
	void getStatus() {
		assertEquals("Open", sampleTicket.getStatus());
	}
	
	@Test
	void getUsername() {
		assertEquals("User #1", sampleTicket.getUsername());
	}
	
	@Test
	void getAssignee() {
		assertEquals("Assignee #1", sampleTicket.getAssignee());
	}
	
	@Test
	void getCategory() {
		assertEquals("General", sampleTicket.getCategory());
	}
	
	@Test
	void getDateOpened() {
		assertEquals(sampleTicketDateOpenedTimestamp, sampleTicket.getDateOpened());
	}
	
	@Test
	void testSetPriorityColor() {
		assertEquals(Ticket_untitled.TICKET_PRIORITY_COLOUR_TRIVIAL, sampleTicket.getPriorityColour());
	}
	
	@Test
	void testSetDateClosed() {
		// Create a new date closed Timestamp and increase it by 5 minutes
		Timestamp sampleTicketDateClosedTimestamp = getCurrentTime();
		final long fiveMinutesLater = ((5 * 60)*1000);
		sampleTicketDateClosedTimestamp.setTime(sampleTicketDateOpenedTimestamp.getTime() + fiveMinutesLater);
		sampleTicket.setDateClosed(sampleTicketDateClosedTimestamp);
		assertEquals(sampleTicketDateClosedTimestamp, sampleTicket.getDateClosed());
		// New dateClosed Timestamp should not match dateOpened Timestamp
		assertNotEquals(sampleTicketDateClosedTimestamp, sampleTicketDateOpenedTimestamp);
	}
	
	@Test
	void testFormDateOpen() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String formDateOpen = dateFormat.format(sampleTicketDateOpenedTimestamp);
		assertEquals(formDateOpen, sampleTicket.getFormDateOpen());
		// Formatted date should not match the database Timestamp
		assertNotEquals(formDateOpen, sampleTicket.getDateOpened());
	}
	
	/**
	 * Initializes sampleTicket object for testing
	 */
	public void initializeSampleTicket() {
		sampleTicket = new Ticket_untitled();
		sampleTicket.setTicketID(1);
		sampleTicket.setTitle("Sample Ticket Title #1");
		sampleTicket.setDescription("Sample Ticket Description #1");
		sampleTicket.setPriority(Ticket_untitled.TICKET_PRIORITY_TRIVIAL);
		sampleTicket.setStatus(Ticket_untitled.TICKET_STATUS_OPEN);
		sampleTicket.setUsername("User #1");
		sampleTicket.setAssignee("Assignee #1");
		sampleTicket.setCategory(Ticket_untitled.TICKET_CATEGORY_GENERAL);
		sampleTicket.setDateOpened(getCurrentTime());
		sampleTicket.setFormattedDateOpen();
	}
	
	/**
	 * Creates a Timestamp object for the current time, sets the timestamp variable for the sampleTicket tests
	 * @return Timestamp object representing the current time 
	 */
	public Timestamp getCurrentTime() {
		sampleTicketDateOpenedTimestamp = new Timestamp(new Date().getTime());
		return sampleTicketDateOpenedTimestamp;
	}

}
