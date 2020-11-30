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
	private static final String ASSIGNED_TICKET_API_METHOD = "ASSIGNED_TICKET";
	
	// Slack API URLs
	private final String GET_SLACK_USER_ID_URL = "https://slack.com/api/users.lookupByEmail";
	
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
	
	public void createTicketNotification(User_untitled user, Ticket_untitled ticket) {
		postSlackNotification(user, ticket, slackEmployeeChannelId, CREATE_TICKET_API_METHOD);
	}
	
	public void assignedTicketNotification(User_untitled user, Ticket_untitled ticket, String slackUserId) {
		postSlackNotification(user, ticket, slackUserId, ASSIGNED_TICKET_API_METHOD);
	}
	
	private void postSlackNotification(User_untitled user, Ticket_untitled ticket, String slackChannel, String slackNotificationType) {
		HttpHeaders headers = new HttpHeaders();
				
		// Add parameters and encode URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(slackAppNotificationUrl)
															.queryParam("channel", slackChannel)
															.queryParam("username", user.getUsername())
															.queryParam("description", ticket.getDescription())
															.queryParam("title", ticket.getTitle())
															.queryParam("priority", ticket.getPriority())
															.queryParam("category", ticket.getCategory())
															.queryParam("method", slackNotificationType);
		
		// Send out request
		HttpEntity<String> request = new HttpEntity<>(headers);
		restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, String.class);
	}
}
