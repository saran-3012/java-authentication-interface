package com.saran.models;

public class User {
	private Integer uid;
	private String fullname;
	private String username;
	private String email;
	private String pass;
	
	public User() {
		this.uid = null;
		this.fullname = null;
		this.username = null;
		this.email = null;
		this.pass = null;
	}
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
