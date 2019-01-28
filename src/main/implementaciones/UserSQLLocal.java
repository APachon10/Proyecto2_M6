package main.implementaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.interfaces.IUser;
import main.modelos.Users;

public class UserSQLLocal implements IUser {

	private Users userLogged;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

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
				System.out.println(resultSet.getString(5));
				userLogged = new Users(resultSet.getInt("userID"), resultSet.getInt("groupID"),
						resultSet.getInt("permisoID"), resultSet.getString("nickname"),
						resultSet.getString("complete_name"), resultSet.getString("password"),
						resultSet.getString("mail"));
			}

			statement.close();

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
		return null;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
