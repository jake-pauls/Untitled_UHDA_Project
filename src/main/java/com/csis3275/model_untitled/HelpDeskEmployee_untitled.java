package com.csis3275.model_untitled;

/**
 * @author Jacob Pauls
 * @date Oct 25, 2020
 * Untitled_UHDA_Project
 */

public class HelpDeskEmployee_untitled extends User_untitled {
	public String department;
	public String jobTitle;
	
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
}
