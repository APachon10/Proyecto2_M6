package main.interfaces;

import java.util.ArrayList;
import main.modelos.Especificaciones;

public interface IEspecificaciones {
	public ArrayList<Especificaciones> getEspecificacionesByProjectID(int id);
	public void insertarEspecificacion(Especificaciones esp,boolean r);
	public void establecerConexion();
	public void cerrarConexion();
}
