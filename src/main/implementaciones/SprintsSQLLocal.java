package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.interfaces.ISprint;
import main.modelos.Sprints;

public class SprintsSQLLocal implements ISprint{
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	@Override
	public ArrayList<Sprints> getSprintsByProjectD(int id) {
		ArrayList<Sprints> sp = new ArrayList<>();
		try {
			establecerConexion();
			statement = connection.createStatement();

			String query = "select * from sprints where projectID="+id;
				resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				Sprints esp = new Sprints();
				esp.setSprintID(resultSet.getInt("sprintID"));
				esp.setProjectID(resultSet.getInt("projectID"));
				sp.add(esp);
			}
			statement.close();
			cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
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

}
