/**
 * 
 */
package com.csis3275.model_untitled;

import java.sql.Timestamp;

/**
 * @author Michael Wilson 300278118
 * @date Nov. 23, 2020
 * Comment_mwi_18.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */
public class Comment_mwi_18 {
	private int commentId;
	private int ticketId;
	private String author;
	private String value;
	private Timestamp dateCreated;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	
}
