package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.interfaces.IEspecificaciones;
import main.interfaces.IProject;
import main.modelos.Especificaciones;
import main.modelos.Proyectos;

public class EspecificacionesSQLLocal implements IEspecificaciones {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	@Override
	public ArrayList<Especificaciones> getEspecificacionesByProjectID(int id) {
		ArrayList<Especificaciones> esp = new ArrayList<>();
		try {
			establecerConexion();
			statement = connection.createStatement();

			String query = "select * from specifications where projectID=" + id;
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Especificaciones espec = new Especificaciones();
				espec.setSpecID(resultSet.getInt("specID"));
				espec.setDescription(resultSet.getString("description"));
				espec.setHoras(resultSet.getInt("horas"));
				espec.setProjectID(resultSet.getInt("specID"));
				espec.setSprintID(resultSet.getInt("sprintID"));

				esp.add(espec);
			}
			statement.close();
			cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return esp;
	}

	@Override
	public void establecerConexion() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cerrarConexion() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertarEspecificacion(Especificaciones esp) {
		try {
			establecerConexion();
			statement = connection.createStatement();
			String consulta = "insert into specifications" + " VALUES(" + esp.getSpecID() + ",'" + esp.getDescription()
					+ "'," + esp.getHoras() + "," + esp.getProjectID() + "," + esp.getSprintID() + ");";

			this.statement.executeUpdate(consulta);
			this.statement.close();

			cerrarConexion();
			//ficheroReplica(esp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void ficheroReplica(Especificaciones esp) {

	}
}
