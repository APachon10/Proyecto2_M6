package main.interfaces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import main.modelos.Users;
import main.modelos.Permisos;
import main.modelos.Proyectos;

public interface IUser {
    
	Users user = new Users();
	ArrayList<Users> users = new ArrayList<>();
	ArrayList<Permisos> permisos = new ArrayList<>();
	
	// Methods;
	public Users getUserLogin(String userName,String password);
	public String getTitleConnection();
	public Users getUserLogged();
	public String getUserLoggedPermission();
	public void añadirUsuario(Users user);
	
	/*
	 * Print all users of the ArrayList<User>
	 */
//	public default void getAllUsers() {
//		if(users.size() != 0) {
//			for (Users user : users) {
//				System.out.println(user.toString());
//			}
//		}else {
//			System.err.println("No se han cargado los datos de los usuarios correctamente.");
//		}
//	}
}
