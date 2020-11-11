package com.csis3275.integration_tests_untitled;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.csis3275.dao_untitled.TicketManagementDAOImpl_jpa_66;
import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.test_config_untitled.H2TestConfig_jpa_66;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 11, 2020
 * TicketIntegrationTest_jpa_66.java
 * com.csis3275.integration_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2TestConfig_jpa_66.class, loader = AnnotationConfigContextLoader.class)
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
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		initializeSampleTicket();
	}

	@Test
	void testCreateTicket() {
		ticketDAO.createTicket(sampleTicket);
		// 25 Sample Records, set the ticket to be the next inserted
		sampleTicket.setTicketID(26);
		Ticket_untitled retrievedTicket = ticketDAO.getTicketByID(Integer.toString(sampleTicket.getTicketID()));
		System.out.println(retrievedTicket.getTitle());
		assertNotNull(retrievedTicket);
		assertEquals(retrievedTicket.getTicketID(), sampleTicket.getTicketID());
	}
	
	@Test
	void testGetTicketByID() {
		Ticket_untitled testTicket = ticketDAO.getTicketByID("1");
		assertNotNull(testTicket);
		assertEquals(1, testTicket.getTicketID());
	}
	
	/**
	 * Initializes sampleTicket object for testing
	 */
	public void initializeSampleTicket() {
		sampleTicket = new Ticket_untitled();
		sampleTicket.setTitle("Sample Ticket Title #11");
		sampleTicket.setDescription("Sample Ticket Description #11");
		sampleTicket.setPriority(Ticket_untitled.TICKET_PRIORITY_TRIVIAL);
		sampleTicket.setStatus(Ticket_untitled.TICKET_STATUS_OPEN);
		sampleTicket.setUsername("admin");
		sampleTicket.setAssignee("employee");
		sampleTicket.setCategory(Ticket_untitled.TICKET_CATEGORY_GENERAL);
		sampleTicket.setDateOpened(getCurrentTime());
		sampleTicket.setFormattedDateOpen();
	}
	
	/**
	 * Creates a Timestamp object for the current time, sets the timestamp variable for the sampleTicket tests
	 * @return Timestamp object representing the current time 
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}


}
