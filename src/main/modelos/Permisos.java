package main.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permisos")
public class Permisos {
	private Integer permisoID;
	private String permiso_name;
	
	/*Getters */
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPermisoID() {
		return permisoID;
	}
	@Column
	public String getPermiso_name() {
		return permiso_name;
	}
	//Setters
	public void setPermisoID(Integer permisoID) {
		this.permisoID = permisoID;
	}
	public void setPermiso_name(String permiso_name) {
		this.permiso_name = permiso_name;
	}
	
	
	
}
