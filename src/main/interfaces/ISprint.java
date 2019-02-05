package main.interfaces;

import java.util.ArrayList;

import main.modelos.Sprints;

public interface ISprint {
	public ArrayList<Sprints> getSprintsByProjectD(int id);
	public void establecerConexion();
	public void cerrarConexion();
}
