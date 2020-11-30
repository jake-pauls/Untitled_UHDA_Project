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
	private String hardwareTypeName;
	private String status;
	private String usernameAssignedTo;
	private Timestamp dateAssigned;
	private Timestamp dateReturned;
	public int getHardwareID() {
		return hardwareID;
	}
	public void setHardwareID(int hardwareID) {
		this.hardwareID = hardwareID;
	}
	public String getHardwareName() {
		return hardwareTypeName;
	}
	public void setHardwareName(String hardwareTypeName) {
		this.hardwareTypeName = hardwareTypeName;
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
		return dateAssigned;
	}
	public void setDateAssigned(Timestamp dateAssigned) {
		this.dateAssigned = dateAssigned;
	}
	public Timestamp getDateReturned() {
		return dateReturned;
	}
	public void setDateReturned(Timestamp dateReturned) {
		this.dateReturned = dateReturned;
	}
	
}
