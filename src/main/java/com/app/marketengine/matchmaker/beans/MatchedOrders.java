package com.app.marketengine.matchmaker.beans;

public class MatchedOrders {
	String BuyerID;
	String SellerID;
	
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
	
	public MatchedOrders(String buyerID, String sellerID) {
		super();
		BuyerID = buyerID;
		SellerID = sellerID;
	}
	
}
