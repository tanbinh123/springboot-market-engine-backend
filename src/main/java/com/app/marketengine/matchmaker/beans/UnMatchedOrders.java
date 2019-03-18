package com.app.marketengine.matchmaker.beans;

public class UnMatchedOrders {
	String userID;
	Integer price;
	
	public UnMatchedOrders(String userID, Integer price) {
		super();
		this.userID = userID;
		this.price = price;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
}
