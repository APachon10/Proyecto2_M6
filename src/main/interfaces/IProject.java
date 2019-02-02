package main.interfaces;

import main.modelos.Proyectos;

public interface IProject {
	
	public void insertarProyecto(Proyectos proy);
	public String getProjectName(Proyectos proy);
	public void establecerConexion();
	public void cerrarConexion();
}
