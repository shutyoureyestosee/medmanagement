package com.application.payload;




import java.util.HashSet;
import java.util.Set;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



public class UserDto {

	private int id;

	
	private String name;

	
	private String email;

	


	private String password;

	
	
	private String role ;
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}

}
