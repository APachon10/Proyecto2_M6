package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IEspecificaciones;
import main.modelos.Especificaciones;

public class EspecificacionesSQLRemota implements IEspecificaciones {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private EntityManagerFactory factory;
	private EntityManager entityManager;

	public EspecificacionesSQLRemota() {
		factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		entityManager = factory.createEntityManager();
	}

	@Override
	public ArrayList<Especificaciones> getEspecificacionesByProjectID(int id) {
		ArrayList<Especificaciones> esp = (ArrayList<Especificaciones>) this.entityManager
				.createQuery("select e from Especificaciones e where projectID=" + id).getResultList();
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
}
