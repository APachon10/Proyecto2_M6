package main.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sprints")
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
	@Column(name ="sprintID")
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	public Integer getSprintID() {
		return sprintID;
	}
	
	@Column(name ="projectID")
	public Integer getProjectID() {
		return projectID;
	}

}
