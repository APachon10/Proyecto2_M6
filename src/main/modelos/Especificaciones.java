package main.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="specifications")
public class Especificaciones {
	private Integer specID,projectID,SprintID,horas;
	private String description;

	
	//Setters
	public void setSpecID(Integer specID) {
		this.specID = specID;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
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
	@Column(name="specID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSpecID() {
		return specID;
	}
	
	@Column(name="projectID")
	public Integer getProjectID() {
		return projectID;
	}
	
	@Column(name="horas")
	public Integer getHoras() {
		return horas;
	}
	
	@Column(name="sprintID")
	public Integer getSprintID() {
		return SprintID;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
}
