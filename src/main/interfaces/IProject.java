package main.interfaces;

import java.util.ArrayList;

import main.modelos.Proyectos;
import main.modelos.Users;

public interface IProject {
	
	public void insertarProyecto(Proyectos proy);
	public String getProjectName(Proyectos proy);
	public ArrayList<Proyectos> getProjectsByID(IUser iuser);
	public void establecerConexion();
	public void cerrarConexion();
}
