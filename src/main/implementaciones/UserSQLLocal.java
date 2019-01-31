package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.interfaces.IUser;
import main.modelos.Proyectos;
import main.modelos.Users;

public class UserSQLLocal implements IUser {

	private Users userLogged;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public UserSQLLocal() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Users getUserLogin(String userNickname, String password) {
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
			System.out.println("Conexion embebida conectada.");

			statement = connection.createStatement();
			String query = "select * from users where nickname = '" + userNickname + "' and password = '" + password
					+ "';";

			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				userLogged = new Users(resultSet.getInt("userID"), resultSet.getInt("groupID"),
						resultSet.getInt("permisoID"), resultSet.getString("nickname"),
						resultSet.getString("complete_name"), resultSet.getString("password"),
						resultSet.getString("mail"));
			}

			statement.close();
			this.connection.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userLogged;
	}

	@Override
	public String getTitleConnection() {
		return " (Offline)";
	}

	@Override
	public Users getUserLogged() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userLogged ;
	}

	@Override
	public String getUserLoggedPermission() {
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
			statement = connection.createStatement();


			String query = "select * from permisos where permisoID = " + userLogged.getPermiso_id();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				return resultSet.getString("permiso_name");
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void añadirUsuario(Users user) {
		try {
			this.statement = connection.createStatement();
			if (this.connection != null) {
				this.connection = DriverManager
						.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
				String consulta = "insert into users" + " VALUES(" + user.getUserID() + ", '" + user.getNickname()
						+ "', '" + user.getComplete_name() + "', '" + user.getPassword() + "', '" + user.getMail()
						+ "', " + null + ", " + user.getPermiso_id() + ");";
				System.out.println(consulta);
				this.statement.executeUpdate(consulta);
				this.statement.close();
				this.connection.close();
			} else {
				System.out.println("eee no hay conexion men");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Users> getScrumMasterUsers() {
		ArrayList<Users> smUsers=new ArrayList<>();
		
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
			statement = connection.createStatement();

			String query = "select * from users where permisoID = " + 3;
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Users user=new Users();
				user.setUserID(resultSet.getInt("userID"));
				user.setNickname(resultSet.getString("nickname"));
				user.setPassword(resultSet.getString("password"));
				user.setComplete_name(resultSet.getString("complete_name"));
				user.setMail(resultSet.getString("mail"));
				user.setGroup_id(null);
				user.setPermiso_id(resultSet.getInt("permisoID"));
				smUsers.add(user);
			}

			this.connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return smUsers;
	}

	@Override
	public ArrayList<Users> getProductOwnerUsers() {

		ArrayList<Users> poUsers=new ArrayList<>();
		
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrumprojectmanager.db");
			statement = connection.createStatement();

			String query = "select * from users where permisoID = " + 4;
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Users user=new Users();
				user.setUserID(resultSet.getInt("userID"));
				user.setNickname(resultSet.getString("nickname"));
				user.setPassword(resultSet.getString("password"));
				user.setComplete_name(resultSet.getString("complete_name"));
				user.setMail(resultSet.getString("mail"));
				user.setGroup_id(null);
				user.setPermiso_id(resultSet.getInt("permisoID"));
				poUsers.add(user);
			}

			this.connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return poUsers;
	}
}