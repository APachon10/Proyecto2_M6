package main.interfaces;

import java.util.ArrayList;
import main.modelos.Proyectos;

public interface IProject {
	
	public void insertarProyecto(Proyectos proy,boolean r);
	public String getProjectName(Proyectos proy);
	public ArrayList<Proyectos> getProjectsByID(IUser iuser);
	public void establecerConexion();
	public void cerrarConexion();
}
