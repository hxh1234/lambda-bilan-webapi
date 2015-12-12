package com.lambda.bilan.web.models;

public class Reponse {

	private int status;
	private Object data;

	public Reponse() {
	}

	public Reponse(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
