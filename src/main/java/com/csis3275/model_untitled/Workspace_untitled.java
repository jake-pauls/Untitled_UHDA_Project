package com.csis3275.model_untitled;

import java.awt.Image;
import java.sql.Date;

/**
 * @author Jacob Pauls
 * @date Oct 21, 2020
 * Untitled_UHDA_Project
 */

public class Workspace_untitled {
	public String workspaceName;
	private Image workspaceImage;
	private Date dateCreated;

	public String getWorkspaceName() {
		return workspaceName;
	}
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}
	public Image getWorkspaceImage() {
		return workspaceImage;
	}
	public void setWorkspaceImage(Image workspaceImage) {
		this.workspaceImage = workspaceImage;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
