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
	ArrayList<Users> smUsers=new ArrayList<>();
	ArrayList<Users> poUsers=new ArrayList<>();
	
	// Methods;
	public Users getUserLogin(String userName,String password);
	public String getTitleConnection();
	public Users getUserLogged();
	public String getUserLoggedPermission();
	public void añadirUsuario(Users user);
	public ArrayList<Users> getScrumMasterUsers();
	public ArrayList<Users> getProductOwnerUsers();
	public void establecerConexion();
	public void cerrarConexion();
	public String getUserNameByID(int id);
}
