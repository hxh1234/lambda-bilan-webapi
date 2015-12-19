package com.lambda.bilan.web.models;

public class ForgetPasswordModel {
	private long id;
	private String email;
	
	public ForgetPasswordModel() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
