package com.app.marketengine.matchmaker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.app.marketengine.matchmaker.entity.BuyBook;
import com.app.marketengine.matchmaker.repository.BuyBookRepository;

@Service
@ComponentScan(basePackages = "com.app.marketengine.matchmaker")
public class BuyBookServices{
	
	@Autowired
	private BuyBookRepository buyRepo;
	
	public void saveBooks(String userID, Integer price) {
		BuyBook buyOrder= new BuyBook();
		buyOrder.setUserID(userID);
		buyOrder.setPrice(price);
		buyRepo.saveAndFlush(buyOrder);
		System.out.println(buyRepo.count());
	}
	
	public List<BuyBook> getAllBuyOrders(){
		return buyRepo.findAll();
	}

}
