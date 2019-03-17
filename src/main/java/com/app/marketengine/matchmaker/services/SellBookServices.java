package com.app.marketengine.matchmaker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.app.marketengine.matchmaker.entity.BuyBook;
import com.app.marketengine.matchmaker.entity.SellBook;
import com.app.marketengine.matchmaker.repository.SellBookRepository;

@Service
@ComponentScan(basePackages = "com.app.marketengine.matchmaker")
public class SellBookServices{
	
	@Autowired
	private SellBookRepository sellRepo;
	
	public void saveBooks(String userID, Integer price) {
		SellBook sellOrder= new SellBook();
		sellOrder.setUserID(userID);
		sellOrder.setPrice(price);
		sellRepo.save(sellOrder);
	}
	
	public List<SellBook> getAllSellOrders(){
		return sellRepo.findAll();
	}

}
