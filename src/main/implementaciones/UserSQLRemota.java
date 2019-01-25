package main.implementaciones;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
//		int primaryKey = 1;
//
//		while (entityManager.find(Users.class, primaryKey) != null) {
//			this.users.add(entityManager.find(Users.class, primaryKey));
//			primaryKey++;
//		}
//
//		primaryKey = 1;
//
//		while (entityManager.find(Permisos.class, primaryKey) != null) {
//			this.permisos.add(entityManager.find(Permisos.class, primaryKey));
//			primaryKey++;
//		}
	}

	/*
	 * Search within the ArrayList for the user specified in the login screen.
	 * 
	 * @param The Username and the password introduced in the login screen.
	 * 
	 * @return If the username and the password is correct, return the object User
	 * with the information about the user logged.
	 * 
	 * @see main.interfaces.IUser#getUserLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Users getUserLogin(String userName, String password) {
		String passHashed = password;
		for (Users user : users) {
			if (user.getNickname().equals(userName) && user.getPassword().equals(passHashed)) {
				this.userLogged = user;
				System.out.println("[INFO] - Usuario encontrado!");
				System.out.println("====================================");
				System.out.println("Usuario: " + this.userLogged.getNickname());
				System.out.println("Contraseña: " + this.userLogged.getPassword());
				System.out.println("====================================");
				return this.userLogged;
			}
		}
		return null;
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

}
