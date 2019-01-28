package main.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usersprojects")
public class usersProjects {
	private int userID,projectID;
	//Getters
	@Column(name ="userID")
	@Id
	public int getUserID() {
		return userID;
	}
	@Column(name ="projectID")
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
