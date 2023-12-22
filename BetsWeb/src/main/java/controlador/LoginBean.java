package controlador;

import businessLogic.BLFacadeImplementation;
import exceptions.UserAlreadyExists;

public class LoginBean {
	private BLFacadeImplementation facade;
	private String nombre;
	private String password;
	
	public LoginBean() {
		this.facade = new BLFacadeImplementation();
		try {
			this.facade.createUser("christian", "badajoz");
			this.facade.createUser("jon", "onekin");
			this.facade.createUser("admin", "admin");
		} catch (UserAlreadyExists e) {
			System.out.println("Error con la base de datos.");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String comprobar() {
		if (this.facade.checkPassword(this.nombre, this.password)) {
			return "error";
		} else {
			return "ok";
		}
	}
}