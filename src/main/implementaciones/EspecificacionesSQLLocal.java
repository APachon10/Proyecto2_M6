package main.implementaciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.interfaces.IEspecificaciones;
import main.modelos.Especificaciones;

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
	public void insertarEspecificacion(Especificaciones esp,boolean r) {
		try {
			establecerConexion();
			statement = connection.createStatement();
			String consulta = "insert into specifications" + " VALUES(" + esp.getSpecID() + ",'" + esp.getDescription()
					+ "'," + esp.getHoras() + "," + esp.getProjectID() + "," + esp.getSprintID() + ");";

			this.statement.executeUpdate(consulta);
			this.statement.close();

			cerrarConexion();
			ficheroReplica(esp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void ficheroReplica(Especificaciones esp) {
		File replica = new File("src/main/resources/registros");
		if (!replica.exists()) {
			try {
				replica.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String e = "Especificacion-" + esp.getSpecID() + "-" + esp.getDescription() + "-" + esp.getHoras() + "-"
				+ esp.getProjectID() + "-" + esp.getSprintID();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(replica, true));
			bw.write(e);
			bw.append("\r\n");
			bw.close();
		} catch (IOException a) {
			a.printStackTrace();
		}
	}
}
