package main.implementaciones;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IUser;
import main.modelos.Permisos;
import main.modelos.Users;

public class UserSQLRemota implements IUser {

	private Users userLogged;

	/*
	 * This constructor load the users from the Remote Database to ArrayList<User>
	 */
	public UserSQLRemota() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		EntityManager entityManager = factory.createEntityManager();
		int primaryKey = 1;
		
//		while (entityManager.find(Users.class, primaryKey) != null) {
//			this.users.add(entityManager.find(Users.class, primaryKey));
//			primaryKey++;
//		}

		
		
		primaryKey = 1;
		while (entityManager.find(Permisos.class, primaryKey) != null) {
			this.permisos.add(entityManager.find(Permisos.class, primaryKey));
			primaryKey++;
		}
	}

	@Override
	public Users getUserLogin(String userName, String password) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
		EntityManager entityManager = factory.createEntityManager();
		
		List<Users> usuario=entityManager.createQuery("select x from Users x where nickname = '"+userName+"' and password = '"+password+"'").getResultList();
		
		userLogged=usuario.get(0);
		
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
					"[ERROR] - No hay ning�n usuario logeado, por lo tanto no se puede obtener el tipo de usuario que es.");
		}

		return null;
	}

}
