package com.zzxt.entity;

import java.io.Serializable;

public class UserTest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6199772149567120822L;
	private String username;
	private String password;

	@Override
	public String toString() {
		return "UserTest [username=" + username + ", password=" + password + "]";
	}

	public UserTest() {

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

	public UserTest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
