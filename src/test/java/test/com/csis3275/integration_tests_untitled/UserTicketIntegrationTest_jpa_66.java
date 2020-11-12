package test.com.csis3275.integration_tests_untitled;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 11, 2020
 * UserTicketIntegrationTest_jpa_66.java
 * com.csis3275.integration_tests_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

class UserTicketIntegrationTest_jpa_66 {
	
	// Sample Ticket
	Ticket_untitled sampleTicket;
	
	// Sample User
	User_untitled sampleUser;
	User_untitled sampleAdmin;
	
	// Testing constants
	private static final String SAMPLE_USER_USERNAME = "NewUser";
	private static final String SAMPLE_ADMIN_USERNAME = "NewAdmin";
	
	@BeforeEach
	void setUpBeforeEach() {
		initializeSampleTicket();
		initializeSampleUser();
		initializeSampleAdmin();
	}
	
	@Test
	void testTicketUsername() {
		// Set the username for the sample ticket based on the sampleUser
		sampleTicket.setUsername(sampleUser.getUsername());
		assertEquals(sampleTicket.getUsername(), SAMPLE_USER_USERNAME);
	}
	
	@Test
	void testTicketAssignment() {
		// Set the assignee for the sample ticket based on the sampleAdmin
		sampleTicket.setAssignee(sampleAdmin.getUsername());
		assertEquals(sampleTicket.getAssignee(), SAMPLE_ADMIN_USERNAME);
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
		sampleTicket.setCategory(Ticket_untitled.TICKET_CATEGORY_GENERAL);
	}
	
	/**
	 * Initializes sampleUser object for testing
	 */
	public void initializeSampleUser() {
		sampleUser = new User_untitled();
		sampleUser.setUsername(SAMPLE_USER_USERNAME);
		sampleUser.setFirstName("TestFirstName");
		sampleUser.setLastName("TestLastName");
		sampleUser.setEmail("UHDATesting@test.com");
		sampleUser.setRole("user");
	}
	
	/**
	 * Initializes sampleAdmin object for testing
	 */
	public void initializeSampleAdmin() {
		sampleAdmin = new User_untitled();
		sampleAdmin.setUsername(SAMPLE_ADMIN_USERNAME);
		sampleAdmin.setFirstName("TestFirstName");
		sampleAdmin.setLastName("TestLastName");
		sampleAdmin.setEmail("UHDATesting@test.com");
		sampleAdmin.setRole("admin");
	}
	
	
}
