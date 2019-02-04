package main.interfaces;

import java.util.ArrayList;

import main.modelos.Especificaciones;
import main.modelos.Proyectos;

public interface IEspecificaciones {
	public ArrayList<Especificaciones> getEspecificacionesByProjectID(int id);
	public void establecerConexion();
	public void cerrarConexion();
}
