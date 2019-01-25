package Tablas;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

public class usersProjects {
	private int userID,projectID;
	//Getters
	@Column(name = "userID")
	public int getUserID() {
		return userID;
	}
	@Column(name = "projectID")
	public int getProjectID() {
		return projectID;
	}
	//Setters
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
}
