package com.qianfeng.auction.entity;

import java.io.Serializable;

public class JsonData implements Serializable {

	private int state;
	private String message;
	private Object data;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
