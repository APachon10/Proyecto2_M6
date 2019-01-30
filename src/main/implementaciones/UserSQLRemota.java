package main.implementaciones;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IUser;
import main.modelos.Permisos;
import main.modelos.Proyectos;
import main.modelos.Users;
import main.vistas.VentanaProyecto;

public class UserSQLRemota implements IUser {

	private Users userLogged;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private Connection connection;
	private Statement statement;

	/*
	 * This constructor load the users from the Remote Database to ArrayList<User>
	 */
	public UserSQLRemota() {
		factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		entityManager = factory.createEntityManager();
		int primaryKey = 1;

		// while (entityManager.find(Users.class, primaryKey) != null) {
		// this.users.add(entityManager.find(Users.class, primaryKey));
		// primaryKey++;
		// }

		primaryKey = 1;
		while (entityManager.find(Permisos.class, primaryKey) != null) {
			this.permisos.add(entityManager.find(Permisos.class, primaryKey));
			primaryKey++;
		}
	}

	@Override
	public Users getUserLogin(String userName, String password) {
		factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		entityManager = factory.createEntityManager();

		List<Users> usuario = entityManager
				.createQuery(
						"select x from Users x where nickname = '" + userName + "' and password = '" + password + "'")
				.getResultList();

		userLogged = usuario.get(0);

		return userLogged;
	}

	/*
	 * Return status of connection.
	 * 
	 * @return A String about the state of the connection.
	 * 
	 * @see main.interfaces.IUser#getTitleConnection()
	 */
	@Override
	public String getTitleConnection() {
		return " (Online)";
	}

	/*
	 * Return the Object with the information about the user logged.
	 * 
	 * @return the object about the user logged.
	 * 
	 * @see main.interfaces.IUser#getUserLogged()
	 */
	@Override
	public Users getUserLogged() {
		return this.userLogged;
	}

	/*
	 * Return the name about the permission assigned to the user
	 * 
	 * @return the String with the information about the permission assigned.
	 */
	@Override
	public String getUserLoggedPermission() {
		try {
			for (Permisos userPermission : permisos) {
				if (userPermission.getPermisoID() == userLogged.getPermiso_id()) {
					return userPermission.getPermiso_name();
				}
			}
		} catch (Exception e) {
			System.err.println(
					"[ERROR] - No hay ningún usuario logeado, por lo tanto no se puede obtener el tipo de usuario que es.");
		}

		return null;
	}

	// @Override
	// public Proyectos añadirProyecto() {
	// return null;
	// EntityManagerFactory factory =
	// Persistence.createEntityManagerFactory("scrumprojectmanager");
	// EntityManager entityManager = factory.createEntityManager();
	//
	// entityManager.getTransaction().begin();
	// Proyectos p = new Proyectos();
	// if (p.getProject_name().equals(VentanaProyecto.NameProject.getText())) {
	// VentanaProyecto.ProjectoExistente.setText("El Proyecto" + p.getProject_name()
	// + " ya existe ");
	// } else {
	// p.setProject_name(VentanaProyecto.NameProject.getText());
	// p.setDescripcion(VentanaProyecto.DescArea.getText());
	//
	// }
	// entityManager.close();
	// factory.close();
	// }

	@Override
	public void añadirUsuario(Users user) {
		System.out.println(user.getNickname());
		this.users.add(user);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(user);
		this.entityManager.getTransaction().commit();
		 replicarUser(user);
	}

	private void replicarUser(Users user) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
			statement = connection.createStatement();
			String consulta = "insert into users" + " VALUES('" + user.getUserID() + "', '" +user.getNickname()
					+ "', '" + user.getComplete_name() + "', '" + user.getPassword() + "', '" + user.getMail()
					+ "', "+ null+", " + user.getPermiso_id() + ");";
			statement.executeUpdate(consulta);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
