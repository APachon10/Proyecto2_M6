package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import main.interfaces.IProject;
import main.interfaces.IUser;
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
	public void insertarProyecto(Proyectos proy,boolean r) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(proy);
		this.entityManager.getTransaction().commit();
		if (r) {
			replicaProyectos(proy);	
		}
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

	@Override
	public ArrayList<Proyectos> getProjectsByID(IUser iuser) {

		ArrayList<Proyectos> proyectos = null;
		int permisoID = iuser.getUserLogged().getPermiso_id();

		if (permisoID == 1) {
			proyectos = (ArrayList<Proyectos>) this.entityManager.createQuery(
					"select p from Proyectos p where projectID = (select x.projectID from usersProjects x where userID="
							+ iuser.getUserLogged().getUserID() + ")")
					.getResultList();

		} else if (permisoID == 4) {
			proyectos = (ArrayList<Proyectos>) this.entityManager
					.createQuery("select p from Proyectos p where productOwnerID=" + iuser.getUserLogged().getUserID())
					.getResultList();
		} else if (permisoID == 3) {
			proyectos = (ArrayList<Proyectos>) this.entityManager.createQuery("select p from Proyectos p")
					.getResultList();
		}

		return proyectos;
	}

}
