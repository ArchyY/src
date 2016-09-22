package com.jdbc.DTO;

import java.util.Date;

public class BuyInfoList {
	   private String username;
	   private String bName;
	   private int bCount;
	   private double price;
	   private String press;
	   private String author;
	   private Date buyDate;
	   private double allMoney;
	   private int buyCount;
	   public int recordAllCount;

	   
	   
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(double allMoney) {
		this.allMoney = allMoney;
	}

	
	public int getRecordAllCount() {
		return recordAllCount;
	}
	public void setRecordAllCount(int recordAllCount) {
		this.recordAllCount = recordAllCount;
	}
	public int getbCount() {
		return bCount;
	}
	public void setbCount(int bCount) {
		this.bCount = bCount;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
		
	

}
