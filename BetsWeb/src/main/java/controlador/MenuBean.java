package controlador;

import businessLogic.BLFacadeImplementation;

public class MenuBean {
	private BLFacadeImplementation facade;
	
	public MenuBean() {
		this.facade = new BLFacadeImplementation();
		this.facade.initializeBD();
	}
}
