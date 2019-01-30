package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementaciones.UserSQLLocal;
import main.implementaciones.UserSQLRemota;
import main.interfaces.IUser;
import main.vistas.Loguin;

public class Main {
	private static IUser iuser;
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		try {
			emf = Persistence.createEntityManagerFactory("scrumprojectmanager");
			em = emf.createEntityManager();
			iuser = new UserSQLRemota();
			System.out.println("Online");
		} catch (Exception e) {
			System.out.println("Offline");
			iuser = new UserSQLLocal();
		}
		Loguin login = new Loguin(iuser);
	}
}