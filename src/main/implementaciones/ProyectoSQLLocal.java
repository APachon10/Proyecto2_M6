package main.implementaciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.modelos.Proyectos;

public class ProyectoSQLLocal implements IProject {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	@Override
	public void insertarProyecto(Proyectos proy,boolean r) {
		try {
			establecerConexion();
			statement = connection.createStatement();
			String consulta = "insert into projects" + " VALUES(" + proy.getProjectID() + ", '" + proy.getProject_name()
					+ "', '" + proy.getDescripcion() + "', " + proy.getProductOwnerID() + ", " + proy.getScrumMasterID()
					+ ");";
			this.statement.executeUpdate(consulta);
			this.statement.close();

			cerrarConexion();
			ficheroReplica(proy);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getProjectName(Proyectos proy) {
		Proyectos duplicateProjectName = new Proyectos();
		try {
			establecerConexion();
			statement = connection.createStatement();
			String query = "select * from projects where project_name= '" + proy.getProject_name() + "'";
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				duplicateProjectName.setProject_name(resultSet.getString("project_name"));
			}

			statement.close();
			cerrarConexion();

			return duplicateProjectName.getProject_name();

		} catch (SQLException e) {
			return null;
		}
	}

	public void ficheroReplica(Proyectos proy) {
		File replica = new File("src/main/resources/registros");
		if (!replica.exists()) {
			try {
				replica.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String p = "Proyecto-" + proy.getProjectID() + "-" + proy.getProject_name() + "-" + proy.getDescripcion() + "-"
				+ proy.getProductOwnerID() + "-" + proy.getScrumMasterID();
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(replica,true));
			bw.write(p);
			bw.append("\r\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
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
		ArrayList<Proyectos> proyectos = new ArrayList<>();
		try {
			establecerConexion();
			statement = connection.createStatement();

			String query;

			int permisoID = iuser.getUserLogged().getPermiso_id();

			if (permisoID == 1) {
				query = "select * from projects where projectID = (select projectID from usersProjects where userID="
						+ iuser.getUserLogged().getUserID() + ")";
				resultSet = statement.executeQuery(query);
			} else if (permisoID == 4) {
				query = "select * from projects where productOwnerID=" + iuser.getUserLogged().getUserID();
				resultSet = statement.executeQuery(query);
			} else if (permisoID == 3) {
				query = "select * from projects";
				resultSet = statement.executeQuery(query);
			}

			while (resultSet.next()) {
				Proyectos proy = new Proyectos();
				proy.setProjectID(resultSet.getInt("projectID"));
				proy.setProject_name(resultSet.getString("project_name"));
				proy.setDescripcion(resultSet.getString("descripcion"));
				proy.setScrumMasterID(resultSet.getInt("scrumMasterID"));
				proy.setProductOwnerID(resultSet.getInt("productOwnerID"));
				proyectos.add(proy);
			}
			statement.close();
			cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proyectos;
	}
}
