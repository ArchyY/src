package com.jdbc.entity;

import java.util.Date;
import java.util.List;

import javax.management.loading.PrivateClassLoader;


public class BuyRecord {
    private int buyID;
    private int userID;
    private Date buyTime;
    private double allMoney;
    private int buyCount;
	private BuyDetail buyDetail;
	private List<BuyDetail> buyList;
    
    
    public int getBuyID() {
		return buyID;
	}
	public void setBuyID(int buyID) {
		this.buyID = buyID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	public double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(double allMoney) {
		this.allMoney = allMoney;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	
	public BuyDetail getBuyDetail() {
		return buyDetail;
	}
	public void setBuyDetail(BuyDetail buyDetail) {
		this.buyDetail = buyDetail;
	}
	public List<BuyDetail> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<BuyDetail> buyList) {
		this.buyList = buyList;
	}
  
    
}
