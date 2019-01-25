package main.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="projects")
public class Proyectos {
	private Integer projectID;
	private String project_name,descripcion;
	
	
	//Getters
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProjectID() {
		return projectID;
	}
	@Column
	public String getProject_name() {
		return project_name;
	}
	@Column
	public String getDescripcion() {
		return descripcion;
	}
	//Setters
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
