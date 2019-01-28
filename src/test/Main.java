package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementaciones.UserSQLLocal;
import main.implementaciones.UserSQLRemota;
import main.interfaces.IUser;
import main.vistas.Login;

public class Main {
	private static IUser iuser;
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		try {
			// Connection to SQL Remote
			emf = Persistence.createEntityManagerFactory("scrumprojectmanager");
			em = emf.createEntityManager();
			iuser = new UserSQLRemota();
			System.out.println("[INFO] - Conexión Online");
		} catch (Exception e) {
			System.out.println("[INFO] - Conexión Offline " + e);
			iuser = new UserSQLLocal();
		}
		Login login = new Login(iuser);
		IUser iuser = new UserSQLLocal();
		iuser.getAllUsers();
	}
}
