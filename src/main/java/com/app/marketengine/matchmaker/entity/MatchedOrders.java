package com.app.marketengine.matchmaker.entity;

public class MatchedOrders {
	String BuyerID;
	String SellerID;
	Integer price;
	public String getBuyerID() {
		return BuyerID;
	}
	public void setBuyerID(String buyerID) {
		BuyerID = buyerID;
	}
	public String getSellerID() {
		return SellerID;
	}
	public void setSellerID(String sellerID) {
		SellerID = sellerID;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public MatchedOrders(String buyerID, String sellerID, Integer price) {
		super();
		BuyerID = buyerID;
		SellerID = sellerID;
		this.price = price;
	}
	
}
