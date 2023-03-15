package com.application.payload;


public class JwtAuthResponse {

	private String token;
	
	private UserDto user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + ", user=" + user + "]";
	}

	public JwtAuthResponse(String token, UserDto user) {
		super();
		this.token = token;
		this.user = user;
	}

	public JwtAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
