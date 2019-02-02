package main.implementaciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.interfaces.IProject;
import main.modelos.Proyectos;
import main.modelos.Users;

public class ProyectoSQLLocal implements IProject{
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private EntityManagerFactory factory;
	private EntityManager entityManager;

	@Override
	public void insertarProyecto(Proyectos proy) {		
		try {
			establecerConexion();
				statement = connection.createStatement();
				String consulta = "insert into projects" + " VALUES(" + proy.getProjectID() + ", '" + proy.getProject_name()
						+ "', '" + proy.getDescripcion() + "', " + proy.getProductOwnerID() + ", " + proy.getScrumMasterID()
						+ ");";
				this.statement.executeUpdate(consulta);
				this.statement.close();
				
				cerrarConexion();
				ficheroReplica(proy);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getProjectName(Proyectos proy) {
		Proyectos duplicateProjectName = new Proyectos();
		try {
			establecerConexion();
			statement = connection.createStatement();
			String query = "select * from projects where project_name= '" + proy.getProject_name() + "'";
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				duplicateProjectName.setProject_name(resultSet.getString("project_name"));
			}
			
			statement.close();
			cerrarConexion();
			
			return duplicateProjectName.getProject_name();
			
		} catch (SQLException e) {
			return null;
		}
	}
	
	public void ficheroReplica(Proyectos proy) {
		File replica=new File("src/main/resources/registros.obj");
		if (!replica.exists()) {
			try {
				replica.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(replica,true));
			oos.writeObject(proy);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
