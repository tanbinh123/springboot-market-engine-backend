package com.app.marketengine.matchmaker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "BUYBOOK")
@IdClass(BuyBook.class)
public class BuyBook implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "userID", nullable = false, unique = true)
	String userID;
	
	@Id
	@Column(name = "price", nullable = false)
	Integer price;

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
