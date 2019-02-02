package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IProject;
import main.modelos.Proyectos;

public class ProyectoSQLRemota implements IProject {
	private Connection connection;
	private Statement statement;
	private EntityManagerFactory factory;
	private EntityManager entityManager;

	public ProyectoSQLRemota() {
		factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		entityManager = factory.createEntityManager();
	}

	@Override
	public void insertarProyecto(Proyectos proy) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(proy);
		this.entityManager.getTransaction().commit();
		replicaProyectos(proy);
	}

	private void replicaProyectos(Proyectos proy) {
		try {
			establecerConexion();
			statement = connection.createStatement();
			String consulta = "insert into projects" + " VALUES(" + proy.getProjectID() + ", '" + proy.getProject_name()
					+ "', '" + proy.getDescripcion() + "', " + proy.getProductOwnerID() + ", " + proy.getScrumMasterID()
					+ ");";
			statement.executeUpdate(consulta);
			statement.close();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getProjectName(Proyectos proy) {
		try {
			Proyectos duplicateProjectName = (Proyectos) this.entityManager
					.createQuery("select p from Proyectos p where project_name= '" + proy.getProject_name() + "'")
					.getSingleResult();
			return duplicateProjectName.getProject_name();
		} catch (Exception e) {
			return null;
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
