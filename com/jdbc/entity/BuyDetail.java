package com.jdbc.entity;

public class BuyDetail {
    
	private int ID;
	private String isbn;
	private int buyID;
	private int bCount;
	private double payMoney;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getBuyID() {
		return buyID;
	}
	public void setBuyID(int buyID) {
		this.buyID = buyID;
	}
	
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public int getbCount() {
		return bCount;
	}
	public void setbCount(int bCount) {
		this.bCount = bCount;
	}
	
	
}
