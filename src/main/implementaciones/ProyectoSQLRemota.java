package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
			statement = connection.createStatement();
			String consulta = "insert into projects" + " VALUES(" + proy.getProjectID() + ", '" + proy.getProject_name()
					+ "', '" + proy.getDescripcion() + "', " + proy.getProductOwnerID() + ", " + proy.getScrumMasterID()
					+ ");";
			statement.executeUpdate(consulta);
			statement.close();
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
