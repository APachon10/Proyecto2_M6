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
	private Integer projectID, productOwnerID, scrumMasterID;
	private String project_name,descripcion;
	
	
	//Getters
	@Column(name="projectID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProjectID() {
		return projectID;
	}
	@Column(name="project_name")
	public String getProject_name() {
		return project_name;
	}
	@Column(name="productOwnerID")
	public Integer getProductOwnerID() {
		return productOwnerID;
	}
	@Column(name="scrumMasterID")
	public Integer getScrumMasterID() {
		return scrumMasterID;
	}
	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	//Setters
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public void setProductOwnerID(Integer productOwnerID) {
		this.productOwnerID = productOwnerID;
	}
	public void setScrumMasterID(Integer scrumMasterID) {
		this.scrumMasterID = scrumMasterID;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
