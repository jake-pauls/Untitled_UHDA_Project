package com.csis3275.utility_untitled;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.csis3275.model_untitled.Ticket_untitled;
import com.csis3275.model_untitled.User_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 24, 2020
 * SlackRestUtilityService_jpa_66.java
 * com.csis3275.utility_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Service
@PropertySource("classpath:slack.properties")
public class SlackRestUtilityService_jpa_66 {
	
	// Success and Error Messages for Verifying Slack Accounts with the UHDA
	public static final String SUCCESS_NOTIFICATION_SLACK_ACCOUNT_CONNECTED = "Slack profile found in workspace! UHDA is now connected to your Slack account.";
	public static final String ERROR_NOTIFICATION_SLACK_ACCOUNT_NOT_FOUND = "Slack profile not found in workspace! Please join the Slack workspace with your UHDA email and try again.";
	public static final String SLACK_ACCOUNT_CONNECTED = "Currently connected with Slack";
	public static final String SLACK_ACCOUNT_NOT_CONNECTED = "Not connected with Slack";
	
	// Fields Defining API Methods
	private static final String CREATE_TICKET_API_METHOD = "CREATE_TICKET";
	private static final String PICKUP_TICKET_API_METHOD = "PICKUP_TICKET";
	private static final String ASSIGN_TICKET_API_METHOD = "ASSIGN_TICKET";
	private static final String STATUS_UPDATE_API_METHOD = "STATUS_UPDATE";
	private static final String PRIORITY_UPDATE_API_METHOD = "PRIORITY_UPDATE";
	
	// Slack API URLs
	private final String GET_SLACK_USER_ID_URL = "https://slack.com/api/users.lookupByEmail";
	
	// Slack Environmental Variables
	@Value("${SLACK_APP_NOTIFICATION_URL}")
	private String slackAppNotificationUrl;
	@Value("${SLACK_BOT_TOKEN}")
	private String slackBotToken;
	@Value("${SLACK_EMPLOYEE_CHANNEL_ID}")
	private String slackEmployeeChannelId;
	
	private RestTemplate restTemplate;
	
	public SlackRestUtilityService_jpa_66() {
		restTemplate = new RestTemplate();
	}
	
	/**
	 * Sends a request to the SlackAPI to check if a UHDA user's email is present in the applicable slack workspace
	 * @param email The user's email address matching their UHDA and Slack accounts
	 * @return String value containing the Slack UserId
	 */
	public String getSlackUserId(String email) {
		String slackUserId = null;
		HttpHeaders headers = new HttpHeaders();
		
		// Add parameters and encode URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_SLACK_USER_ID_URL)
															.queryParam("token", slackBotToken)
															.queryParam("email", email);
		
		// Send out request
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
		
		// Verify response
		if (response.getStatusCode() == HttpStatus.OK) {
			// Parse response and extract the ID
			JSONObject jsonResponse = new JSONObject(response.getBody());
			// Check if user is in workspace
			if (jsonResponse.getBoolean("ok") == true) {
				JSONObject userResponse = jsonResponse.getJSONObject("user");
				slackUserId = userResponse.getString("id");
			} else {
				slackUserId = null;
			}
		}
		
		return slackUserId;
	}
	
	/**
	 * Pushes a notification for a newly created ticket to Slack
	 * @param createdByUser User who created the ticket
	 * @param ticket The Ticket_untitled object containing the new ticket's information
	 */
	public void createTicketNotification(User_untitled createdByUser, Ticket_untitled ticket) {
		postSlackNotification(createdByUser, ticket, slackEmployeeChannelId, CREATE_TICKET_API_METHOD);
	}
	
	/**
	 * Pushes a notification to Slack when a ticket is picked up (by an employee user)
	 * @param pickedUpByUser The user who picked up the ticket
	 * @param ticket The Ticket_untitled object containing the information for the ticket being picked up
	 * @param slackUserId Destination Slack user ID for which the notification will be sent
	 */
	public void pickupTicketNotification(User_untitled pickedUpByUser, Ticket_untitled ticket, String slackUserId) {
		postSlackNotification(pickedUpByUser, ticket, slackUserId, PICKUP_TICKET_API_METHOD);
	}
	
	/**
	 * Pushes a notification to Slack when a ticket's assignment field is updated
	 * @param assignedUser The user who is newly assigned to the ticket
	 * @param ticket The Ticket_untitled object containing the information for the ticket being assigned
	 * @param slackUserId Destination Slack user ID for which the notification will be sent
	 */
	public void assignedTicketNotification(User_untitled assignedUser, Ticket_untitled ticket, String slackUserId) {
		postSlackNotification(assignedUser, ticket, slackUserId, ASSIGN_TICKET_API_METHOD);
	}
	
	/**
	 * Pushes a notification to Slack when a ticket's status field is updated
	 * @param employeeUser The user who performed the status update
	 * @param ticket The Ticket_untitled object containing the information for the ticket in question
	 * @param slackUserId Destination Slack user ID for which the notification will be sent
	 */
	public void statusUpdateTicketNotification(User_untitled employeeUser, Ticket_untitled ticket, String slackUserId) {
		postSlackNotification(employeeUser, ticket, slackUserId, STATUS_UPDATE_API_METHOD);
	}
	
	/**
	 * Pushes a notification to Slack when a ticket's priority field is updated
	 * @param employeeUser The user who performed the priority update
	 * @param ticket The Ticket_untitled object containing the information for the ticket in question
	 * @param slackUserId Destination Slack user ID for which the notification will be sent
	 */
	public void priorityUpdateTicketNotification(User_untitled employeeUser, Ticket_untitled ticket, String slackUserId) {
		postSlackNotification(employeeUser, ticket, slackUserId, PRIORITY_UPDATE_API_METHOD);
	}
	
	/**
	 * Default method to send a REST request to the UHDA Slack app
	 * @param user The User_untitled object for the user whose data should be posted in the notification
	 * @param ticket The Ticket_untitled object for the ticket whose data should be posted in the notification
	 * @param slackChannel The destination channel on slack for which the notification is sent, for user direct messaging refer to the UserID listed in Slack (View Profile > More > Copy member ID)
	 * @param slackNotificationType The notification type being posted, the UHDA app filters this parameter and pushes the appropriate JSON to form the Slack message
	 */
	private void postSlackNotification(User_untitled user, Ticket_untitled ticket, String slackChannel, String slackNotificationType) {
		HttpHeaders headers = new HttpHeaders();
				
		// Add parameters and encode URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(slackAppNotificationUrl)
															.queryParam("channel", slackChannel)
															.queryParam("username", user.getUsername())
															.queryParam("description", ticket.getDescription())
															.queryParam("title", ticket.getTitle())
															.queryParam("priority", ticket.getPriority())
															.queryParam("status", ticket.getStatus())
															.queryParam("category", ticket.getCategory())
															.queryParam("method", slackNotificationType);
		
		// Send out request
		HttpEntity<String> request = new HttpEntity<>(headers);
		restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, String.class);
	}
}
