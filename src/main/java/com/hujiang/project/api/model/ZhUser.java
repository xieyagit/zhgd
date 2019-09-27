package com.hujiang.project.api.model;

public class ZhUser {

	private int id;
	
	private String username;
	
	private String password;
	
	private int port;
	
	public ZhUser() {
		
	}
	
	public ZhUser(int id, String username, String password, int port) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
