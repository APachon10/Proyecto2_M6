package Tablas;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Sprints {
	private Integer sprintID,projectID;
	//Setters
	public void setSprintID(Integer sprintID) {
		this.sprintID = sprintID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	//Getters
	@Column
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	public Integer getSprintID() {
		return sprintID;
	}
	@OneToMany
	@JoinColumn(name = "projectID")
	public Integer getProjectID() {
		return projectID;
	}

}
