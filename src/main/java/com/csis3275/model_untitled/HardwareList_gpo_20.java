package com.csis3275.model_untitled;

import java.sql.Timestamp;

/**
 * 
 * @author Gregory Pohlod Student ID 300311820
 * @date Nov 29, 2020
 * HardwareList_gpo_20.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 *
 */

public class HardwareList_gpo_20 {
	private int hardwareID;
	private String hardwareName;
	private String status;
	private String usernameAssignedTo;
	private Timestamp DateAssigned;
	private Timestamp DateReturned;
	public int getHardwareID() {
		return hardwareID;
	}
	public void setHardwareID(int hardwareID) {
		this.hardwareID = hardwareID;
	}
	public String getHardwareName() {
		return hardwareName;
	}
	public void setHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsernameAssignedTo() {
		return usernameAssignedTo;
	}
	public void setUsernameAssignedTo(String usernameAssignedTo) {
		this.usernameAssignedTo = usernameAssignedTo;
	}
	public Timestamp getDateAssigned() {
		return DateAssigned;
	}
	public void setDateAssigned(Timestamp dateAssigned) {
		DateAssigned = dateAssigned;
	}
	public Timestamp getDateReturned() {
		return DateReturned;
	}
	public void setDateReturned(Timestamp dateReturned) {
		DateReturned = dateReturned;
	}
	
}
