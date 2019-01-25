package Tablas;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class Especificaciones {
	private Integer specID,projectID,SprintID;
	private String description;

	
	//Setters
	public void setSpecID(Integer specID) {
		this.specID = specID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public void setSprintID(Integer sprintID) {
		SprintID = sprintID;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//Getters
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSpecID() {
		return specID;
	}
	@Column(name = "ProjectID")
	public Integer getProjectID() {
		return projectID;
	}
	@Column(name = "SprintID")
	public Integer getSprintID() {
		return SprintID;
	}
	@Column
	public String getDescription() {
		return description;
	}
}
