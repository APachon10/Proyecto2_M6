package main.interfaces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import main.modelos.Users;
import main.modelos.Permisos;

public interface IUser {

	final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    final String NUMERIC = "0123456789";
    final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
    
    
	Users user = new Users();
	ArrayList<Users> users = new ArrayList<>();
	ArrayList<Permisos> permisos = new ArrayList<>();
	
	// Methods;
	public Users getUserLogin(String userName,String password);
	public String getTitleConnection();
	public Users getUserLogged();
	public String getUserLoggedPermission();
	
	/*
	 * Print all users of the ArrayList<User>
	 */
	public default void getAllUsers() {
		if(users.size() != 0) {
			for (Users user : users) {
				System.out.println(user.toString());
			}
		}else {
			System.err.println("No se han cargado los datos de los usuarios correctamente.");
		}
	}
	
	
	/*
	 * This method is responsible for generate a new password for the new user created.
	 * @return the password generated.
	 */
	public default String generatePassword() {
		String dic = ALPHA_CAPS + ALPHA + NUMERIC + SPECIAL_CHARS;
		String password = "";
		
		for (int i = 0; i < 6; i++) {
			int index = new Random().nextInt(dic.length());
	        password += dic.charAt(index);
		}
		
		return password;
	}
}
