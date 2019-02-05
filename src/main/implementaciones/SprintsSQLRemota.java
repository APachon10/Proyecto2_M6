package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.ISprint;
import main.modelos.Especificaciones;
import main.modelos.Sprints;

public class SprintsSQLRemota implements ISprint{
	private Connection connection;
	private Statement statement;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	
	public SprintsSQLRemota() {
		factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		entityManager = factory.createEntityManager();
	}

	@Override
	public ArrayList<Sprints> getSprintsByProjectD(int id) {
		ArrayList<Sprints> sp = (ArrayList<Sprints>) this.entityManager
				.createQuery("select e from Sprints e where projectID=" + id).getResultList();
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
