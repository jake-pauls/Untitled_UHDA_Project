package com.csis3275.model_untitled;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 5, 2020
 * Ticket_untitled.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */


public class Ticket_untitled{
	
	int ticketID;
	String title;
	String description;
	String priority;
	String priorityColour;
	String status;
	String username;
	String assignee;
	String category;
	Timestamp dateOpened;
	Timestamp dateClosed;
	Timestamp lastUpdated;
	String formDateOpen;
	
	List<Comment_mwi_18> comments = new ArrayList<Comment_mwi_18>();
	/*
	 * Ticket Constants
	 * TICKET_PRIORITY - Indicates the possible priority values for a ticket
	 * TICKET_PRIORITY_COLOR - Indicates the possible colour values for the priority fields on a ticket
	 * TICKET_STATUS - Indicates the possible status values for a ticket
	 * TICKET_CATEGORY - Indicates the possible category values for a ticket
	 */
	public static final String TICKET_PRIORITY_UNDER_REVIEW = "Under Review";
	public static final String TICKET_PRIORITY_TRIVIAL = "Trivial";
	public static final String TICKET_PRIORITY_LOW = "Low";
	public static final String TICKET_PRIORITY_NORMAL = "Normal";
	public static final String TICKET_PRIORITY_HIGH = "High";
	public static final String TICKET_PRIORITY_CRITICAL = "Critical";

	public static final String TICKET_PRIORITY_COLOUR_UNDER_REVIEW = "#792fe0";
	public static final String TICKET_PRIORITY_COLOUR_TRIVIAL = "#00ace6";
	public static final String TICKET_PRIORITY_COLOUR_LOW = "#009933";
	public static final String TICKET_PRIORITY_COLOUR_NORMAL = "#1a1a1a";
	public static final String TICKET_PRIORITY_COLOUR_HIGH = "#ffa31a";
	public static final String TICKET_PRIORITY_COLOUR_CRITICAL = "#ff1a1a";

	public static final String TICKET_STATUS_OPEN = "Open";
	public static final String TICKET_STATUS_IN_PROGRESS = "In Progress";
	public static final String TICKET_STATUS_RESOLVED = "Resolved";
	public static final String TICKET_STATUS_CLOSED = "Closed";

	public static final String TICKET_CATEGORY_GENERAL = "General";
	public static final String TICKET_CATEGORY_SOFTWARE = "Software";
	public static final String TICKET_CATEGORY_HARDWARE = "Hardware";
	public static final String TICKET_CATEGORY_INTERNET = "Internet";
	public static final String TICKET_CATEGORY_INSTALLATION_REQUEST = "Installation Request";

	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		switch(priority) {
		case TICKET_PRIORITY_UNDER_REVIEW:
			setPriorityColour(TICKET_PRIORITY_COLOUR_UNDER_REVIEW);
			break;
		case TICKET_PRIORITY_TRIVIAL:
			setPriorityColour(TICKET_PRIORITY_COLOUR_TRIVIAL);
			break;
		case TICKET_PRIORITY_LOW:
			setPriorityColour(TICKET_PRIORITY_COLOUR_LOW);
			break;
		case TICKET_PRIORITY_NORMAL:
			setPriorityColour(TICKET_PRIORITY_COLOUR_NORMAL);
			break;
		case TICKET_PRIORITY_HIGH:
			setPriorityColour(TICKET_PRIORITY_COLOUR_HIGH);
			break;
		case TICKET_PRIORITY_CRITICAL:
			setPriorityColour(TICKET_PRIORITY_COLOUR_CRITICAL);
			break;
		}
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Timestamp getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(Timestamp dateOpened) {
		this.dateOpened = dateOpened;
	}
	public Timestamp getDateClosed() {
		return dateClosed;
	}
	public void setDateClosed(Timestamp dateClosed) {
		this.dateClosed = dateClosed;
	}
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getPriorityColour() {
		return priorityColour;
	}
	public void setPriorityColour(String priorityColour) {
		this.priorityColour = priorityColour;
	}
	
	public void setFormattedDateOpen() {
		
		
		String pattern = "yyyy/MM/dd hh:mm:ss";
		SimpleDateFormat simple = new SimpleDateFormat(pattern);
		
		
		this.formDateOpen = simple.format(this.dateOpened);
		
        
	}
	public String getFormDateOpen() {
		return this.formDateOpen;
	}
	
	public boolean addComment(Comment_mwi_18 comment) {
		return this.comments.add(comment);
	}
	
	public void setComments(List<Comment_mwi_18> list) {
		this.comments = list;
	}
	
	public List<Comment_mwi_18> getComments(){
		return this.comments;
	}
	
	public boolean hasComments() {
		return !this.comments.isEmpty();
	}
}
