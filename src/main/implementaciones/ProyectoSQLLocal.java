package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.interfaces.IProject;
import main.modelos.Proyectos;

public class ProyectoSQLLocal implements IProject{
	private Connection connection;
	private Statement statement;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	
	public ProyectoSQLLocal() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertarProyecto(Proyectos proy) {		
		try {
				this.connection = DriverManager
						.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
				statement = connection.createStatement();
				String consulta = "insert into projects" + " VALUES(" + proy.getProjectID() + ", '" + proy.getProject_name()
						+ "', '" + proy.getDescripcion() + "', " + proy.getProductOwnerID() + ", " + proy.getScrumMasterID()
						+ ");";
				System.out.println(consulta);
				this.statement.executeUpdate(consulta);
				this.statement.close();
				this.connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
