package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String username;
	@Column
	private String password;
	
	public User() { 
		this("default","monkey");
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = hash(password);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPaassowrd(String password) {
		this.password = password;
	}

	public boolean checkPassword(String s) {
		return s.equals(this.password);
	}
	
	private String hash(String s) {
		// TODO Method stub
		return s;
	}
	

}
