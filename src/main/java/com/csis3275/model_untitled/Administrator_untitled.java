package com.csis3275.model_untitled;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Oct 25, 2020
 * Administrator_untitled.java
 * com.csis3275.model_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

public class Administrator_untitled extends User_untitled {
	String department;
	String jobTitle;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Triggers a password reset for a given user
	 * @param user The desired 'User_untitled' object for the password reset to be triggered upon
	 */
	public void triggerUserPasswordReset(User_untitled user) {
		// Trigger password resets for desired users
	}
}
