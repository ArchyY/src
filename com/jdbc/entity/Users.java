package com.jdbc.entity;

import java.util.ArrayList;

public class Users {

	private int userId;
	private String username;
	private String password;
	private int userChara;
	private double balance;

	public Users() {

	}

	public Users(int userId, String username, String password, int userChara) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userChara = userChara;
	}

	public int getUserChara() {
		return userChara;
	}

	public void setUserChara(int userChara) {
		this.userChara = userChara;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	

	public String getChara() {
		String c = null;
		if (userChara == UsersChara.ADMIN) {
			c = "admin";
		}
		if (userChara == UsersChara.VIP) {
			c = "VIP";
		}
		if (userChara == UsersChara.NORMAL_USER) {
			c = "NormalUser";
		}
		return c;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", userChara=" + userChara
				+ ", balance=" + balance + "]";
	}
	
	

}
