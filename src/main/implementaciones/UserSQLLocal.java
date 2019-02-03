package main.implementaciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

	@Override
	public Users getUserLogin(String userNickname, String password) {
		try {
			establecerConexion();

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
			cerrarConexion();

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
		cerrarConexion();
		return userLogged;
	}

	@Override
	public String getUserLoggedPermission() {
		try {
			establecerConexion();
			statement = connection.createStatement();

			String query = "select * from permisos where permisoID = " + userLogged.getPermiso_id();
			resultSet = statement.executeQuery(query);
			String result=null;

			while (resultSet.next()) {
				result = resultSet.getString("permiso_name");
			}
			cerrarConexion();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void añadirUsuario(Users user) {
		try {
				establecerConexion();
				this.statement = connection.createStatement();
				String consulta = "insert into users" + " VALUES(" + user.getUserID() + ", '" + user.getNickname()
						+ "', '" + user.getComplete_name() + "', '" + user.getPassword() + "', '" + user.getMail()
						+ "', " + null + ", " + user.getPermiso_id() + ");";
				this.statement.executeUpdate(consulta);
				this.statement.close();
				cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ficheroReplica(user);
	}

	@Override
	public ArrayList<Users> getScrumMasterUsers() {
		ArrayList<Users> smUsers = new ArrayList<>();

		try {
			establecerConexion();
			statement = connection.createStatement();

			String query = "select * from users where permisoID = " + 3;
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Users user = new Users();
				user.setUserID(resultSet.getInt("userID"));
				user.setNickname(resultSet.getString("nickname"));
				user.setPassword(resultSet.getString("password"));
				user.setComplete_name(resultSet.getString("complete_name"));
				user.setMail(resultSet.getString("mail"));
				user.setGroup_id(null);
				user.setPermiso_id(resultSet.getInt("permisoID"));
				smUsers.add(user);
			}
			
			statement.close();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return smUsers;
	}

	@Override
	public ArrayList<Users> getProductOwnerUsers() {

		ArrayList<Users> poUsers = new ArrayList<>();

		try {
			establecerConexion();
			statement = connection.createStatement();

			String query = "select * from users where permisoID = " + 4;
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Users user = new Users();
				user.setUserID(resultSet.getInt("userID"));
				user.setNickname(resultSet.getString("nickname"));
				user.setPassword(resultSet.getString("password"));
				user.setComplete_name(resultSet.getString("complete_name"));
				user.setMail(resultSet.getString("mail"));
				user.setGroup_id(null);
				user.setPermiso_id(resultSet.getInt("permisoID"));
				poUsers.add(user);
			}

			statement.close();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return poUsers;
	}
	
	public void ficheroReplica(Users user) {
		File replica=new File("src/main/resources/registros.obj");
		if (!replica.exists()) {
			try {
				replica.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(replica,true));
			oos.writeObject(user);
			oos.close();
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
	public String getUserNameByID(int id) {
		Users usuario=null;
		try {
			establecerConexion();
			statement = connection.createStatement();
			String query = "select * from users where userID="+id;
			resultSet = statement.executeQuery(query);
			
			
			while (resultSet.next()) {
				usuario = new Users();
				usuario.setComplete_name(resultSet.getString("complete_name"));			
			}
			
			statement.close();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario.getComplete_name();
	}
}