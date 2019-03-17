package com.app.marketengine.matchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.marketengine.matchmaker.beans.Orders;
import com.app.marketengine.matchmaker.entity.BuyBook;
import com.app.marketengine.matchmaker.entity.SellBook;
import com.app.marketengine.matchmaker.services.BuyBookServices;
import com.app.marketengine.matchmaker.services.SellBookServices;

@RestController
@ComponentScan(basePackages = "com.app.marketengine.matchmaker")
public class MatchMakerMainController {
	
	@Autowired
	private BuyBookServices buyBookServices;
	
	@Autowired
	private SellBookServices sellBookServices;
			
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	
	@PostMapping("/api/v1/orders")
	public <T> ResponseEntity<T> processOrders(@RequestBody Orders orders){
		HttpHeaders responseHeaders = new HttpHeaders();
		System.out.println(orders);
		if(orders.getSaleType().equalsIgnoreCase("Buy")) {
			buyBookServices.saveBooks(orders.getUserID(), Integer.parseInt(orders.getPrice()));
		} else if(orders.getSaleType().equalsIgnoreCase("Sell")) {
			sellBookServices.saveBooks(orders.getUserID(), Integer.parseInt(orders.getPrice()));
		}
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/buyOrders")
	public List<BuyBook> getAllBuyBook(){
		return buyBookServices.getAllBuyOrders();
	}
	
	@GetMapping("/api/v1/sellOrders")
	public List<SellBook> getAllSellBook(){
		return sellBookServices.getAllSellOrders();
	}

}
