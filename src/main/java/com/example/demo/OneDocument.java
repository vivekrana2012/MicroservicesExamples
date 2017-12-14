package com.example.demo;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OneDocument {
	private String _id;
	private String password;
	private String email;
	private String username;
	
	// getter and setter
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getStatus(){
		
		String userName = getUsername();
		String status;
		
		if(userName == null)
			status = "failure";
		else
			status = "success";
			
			return "{\"status\":"+"\""+status+"\""+", \"username\": "+"\""+userName+"\"}";
	}
}
