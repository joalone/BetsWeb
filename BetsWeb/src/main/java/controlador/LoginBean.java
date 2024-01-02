package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacadeImplementation;
import exceptions.UserAlreadyExist;

public class LoginBean {
	private BLFacadeImplementation facade;
	private String nombre;
	private String password;
	
	public LoginBean() {
		this.facade = new BLFacadeImplementation();
		try {
			this.facade.createUser("christian", "badajoz7");
			this.facade.createUser("jon", "onekin42");
			this.facade.createUser("admin", "adminadmin");
		} catch (UserAlreadyExist e) {
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
			return "ok";
		} else {
			return "error";
		}
	}
	
	public void registrar() {
		try {
			this.facade.createUser(this.nombre, this.password);
		} catch (UserAlreadyExist e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: el usuario ya existe."));
		}
	}
}