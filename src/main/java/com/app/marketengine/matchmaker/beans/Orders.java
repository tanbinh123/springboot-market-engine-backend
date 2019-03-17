package com.app.marketengine.matchmaker.beans;

public class Orders {
	String userID;
	String price;
	String saleType;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	public Orders(String userID, String price, String saleType) {
		super();
		this.userID = userID;
		this.price = price;
		this.saleType = saleType;
	}
	
	@Override
	public String toString() {
		return "Orders [userID=" + userID + ", price=" + price + ", saleType=" + saleType + "]";
	}
	
	
	
}
