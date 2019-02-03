package main.implementaciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public UserSQLRemota() {
		factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		entityManager = factory.createEntityManager();
		int primaryKey = 1;

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

	@Override
	public String getTitleConnection() {
		return " (Online)";
	}

	@Override
	public Users getUserLogged() {
		return this.userLogged;
	}

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

	@Override
	public void añadirUsuario(Users user) {
		this.users.add(user);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(user);
		this.entityManager.getTransaction().commit();
		replicarUser(user);
	}

	private void replicarUser(Users user) {
		try {
			establecerConexion();
			statement = connection.createStatement();
			String consulta = "insert into users" + " VALUES('" + user.getUserID() + "', '" +user.getNickname()
					+ "', '" + user.getComplete_name() + "', '" + user.getPassword() + "', '" + user.getMail()
					+ "', "+ null+", " + user.getPermiso_id() + ");";
			statement.executeUpdate(consulta);
			statement.close();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Users> getScrumMasterUsers() {
		ArrayList<Users> perm=new ArrayList<>(this.entityManager.createQuery("select p from Users p where permisoID="+3).getResultList());
		return perm;
	}

	@Override
	public ArrayList<Users> getProductOwnerUsers() {
		ArrayList<Users> perm=new ArrayList<>(this.entityManager.createQuery("select p from Users p where permisoID="+4).getResultList());
		return perm;
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
	public String getUserNameByID(int id) {
		Users user=(Users) entityManager.createQuery("select u from Users u where userID="+id).getSingleResult();	
		return user.getComplete_name();
	}

}