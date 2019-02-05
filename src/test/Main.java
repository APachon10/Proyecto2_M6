package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementaciones.EspecificacionesSQLRemota;
import main.implementaciones.ProyectoSQLRemota;
import main.implementaciones.UserSQLLocal;
import main.implementaciones.UserSQLRemota;
import main.interfaces.IEspecificaciones;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.modelos.Especificaciones;
import main.modelos.Proyectos;
import main.modelos.Users;
import main.vistas.Loguin;

public class Main {
	private static IUser iuser;
	private static IProject ipro;
	private static IEspecificaciones iesp;
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		try {
			emf = Persistence.createEntityManagerFactory("scrumprojectmanager");
			em = emf.createEntityManager();
			iuser = new UserSQLRemota();
			replicarDatos();
			System.out.println("Online");
		} catch (Exception e) {
			System.out.println("Offline");
			iuser = new UserSQLLocal();
		}
		Loguin login = new Loguin(iuser);
	}

	private static void replicarDatos() {
		File replica = new File("src/main/resources/registros");
		ipro = new ProyectoSQLRemota();
		iesp = new EspecificacionesSQLRemota();

		if (replica.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(replica));
				String cadena;
				String[] datos;
				while ((cadena = br.readLine()) != null) {
					datos = cadena.split("-");
					switch (datos[0]) {
					case "User":
						replicarUser(datos);
						break;

					case "Proyecto":
						replicarProyecto(datos);
						break;

					case "Especificacion":
						replicarEspecificacion(datos);
						break;
					}
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(replica));
				bw.write("");
				bw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void replicarEspecificacion(String[] datos) {
		Especificaciones esp=new Especificaciones();
		esp.setDescription(datos[2]);
		esp.setHoras(Integer.parseInt(datos[3]));
		esp.setProjectID(Integer.parseInt(datos[4]));
		esp.setSprintID(Integer.parseInt(datos[5]));
		iesp.insertarEspecificacion(esp,false);
	}

	private static void replicarProyecto(String[] datos) {
		Proyectos proy=new Proyectos();
		proy.setProject_name(datos[2]);
		proy.setDescripcion(datos[3]);
		proy.setProductOwnerID(Integer.parseInt(datos[4]));
		proy.setScrumMasterID(Integer.parseInt(datos[5]));
		ipro.insertarProyecto(proy, false);
	}

	private static void replicarUser(String[] datos) {
		Users user = new Users();
		user.setNickname(datos[2]);
		user.setComplete_name(datos[3]);
		user.setPassword(datos[4]);
		user.setMail(datos[5]);
		user.setGroup_id(null);
		user.setPermiso_id(Integer.parseInt(datos[7]));
		iuser.añadirUsuario(user, false);
	}
}