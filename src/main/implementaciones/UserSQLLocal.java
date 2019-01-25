package main.implementaciones;

import main.interfaces.IUser;
import main.modelos.Users;

public class UserSQLLocal implements IUser{

	@Override
	public Users getUserLogin(String userName, String password) {
		return null;
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
		return null;
	}

}
