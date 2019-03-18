package com.app.marketengine.matchmaker.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.app.marketengine.matchmaker.beans.MatchedOrders;
import com.app.marketengine.matchmaker.beans.UnMatchedOrders;
import com.app.marketengine.matchmaker.entity.BuyBook;
import com.app.marketengine.matchmaker.entity.SellBook;

/**
 * @author Zaveeth
 *
 */

@Service
public class MatchingExecutorService {
	
	
	/**
	 * To be deleted
	 */
	public <T> List<List<T>> getMatchedOrders(List<BuyBook> buyList, List<SellBook> sellList){
		List<List<T>> returnList = new ArrayList<List<T>>();
		List<MatchedOrders> matchedList = new ArrayList<MatchedOrders>();
		List<UnMatchedOrders> unMatchedOrders = new ArrayList<UnMatchedOrders>();
		ConcurrentHashMap<String, Integer> sellerMap = new ConcurrentHashMap<String, Integer>();
		ConcurrentHashMap<String, Integer> buyerMap = new ConcurrentHashMap<String, Integer>();
		for(SellBook s: sellList) {
			sellerMap.put(s.getUserID(), s.getPrice());
		}
		for(BuyBook b: buyList) {
			buyerMap.put(b.getUserID(), b.getPrice());
			for(Map.Entry<String, Integer> set : sellerMap.entrySet()) {
				if(b.getPrice().equals(set.getValue())){
					matchedList.add(new MatchedOrders(b.getUserID(), set.getKey()));
					sellerMap.remove(set.getKey());				
					buyerMap.remove(b.getUserID());
				} 
			}
		}
		
		for(Map.Entry<String, Integer> set : sellerMap.entrySet()) {
				unMatchedOrders.add(new UnMatchedOrders(set.getKey(), set.getValue()));
		}
		
		for(Map.Entry<String, Integer> set : buyerMap.entrySet()) {
			unMatchedOrders.add(new UnMatchedOrders(set.getKey(), set.getValue()));
	}
		
		returnList.add((List<T>) matchedList);
		returnList.add((List<T>) unMatchedOrders);
		return returnList;
	}
	
	public <T> List<List<T>> processOrder(List<BuyBook> buyList, List<SellBook> sellList){
		List<List<T>> returnList = new ArrayList<List<T>>();
		List<MatchedOrders> matchedList = new ArrayList<MatchedOrders>();
		List<UnMatchedOrders> unMatchedOrders = new ArrayList<UnMatchedOrders>();
		
		ConcurrentHashMap<String, Integer> buyerMap = new ConcurrentHashMap<String, Integer>();
		ConcurrentHashMap<String, Integer> sellerMap = new ConcurrentHashMap<String, Integer>();
		ConcurrentHashMap<ConcurrentHashMap<String, String>, Integer> matchedMap = new ConcurrentHashMap<ConcurrentHashMap<String, String>, Integer>();
		ConcurrentHashMap<String, Integer> unMatchedMap = new ConcurrentHashMap<String, Integer>();
		
		for (BuyBook buyer : buyList) {
			buyerMap.put(buyer.getUserID(), buyer.getPrice());
		}
		
		for (SellBook seller : sellList) {
			sellerMap.put(seller.getUserID(), seller.getPrice());
		}
		
//		buyerMap.put("John", 100);
//		buyerMap.put("Jack", 200);
//		buyerMap.put("Raj", 50);
//		buyerMap.put("PP", 80);
		
//		sellerMap.put("Rahul", 50);
//		sellerMap.put("PD", 80);
//		sellerMap.put("Jill", 250);
//		sellerMap.put("Jeff", 200);
		
		ConcurrentHashMap<ConcurrentHashMap<String, String>, Integer> buyerSellerMap = null;
		
		for(Entry<String, Integer> buyer: buyerMap.entrySet()) {
			boolean isMatch = false;
			ConcurrentHashMap<String, String> tempMap = null;
			buyerSellerMap = new ConcurrentHashMap<ConcurrentHashMap<String,String>, Integer>();
			
			for(Entry<String, Integer> seller : sellerMap.entrySet()) {
				int priceDiff = buyer.getValue() - seller.getValue();
				tempMap = new ConcurrentHashMap<String, String>();
				tempMap.put(buyer.getKey(), seller.getKey());
				buyerSellerMap.put(tempMap, priceDiff);
			}
			
			for(Entry<ConcurrentHashMap<String, String>, Integer> entry : buyerSellerMap.entrySet()) {
				int max = Collections.max(buyerSellerMap.values());
				if(entry.getValue() == max && entry.getValue() > -1) {
					matchedMap.put(entry.getKey(), entry.getValue());
					sellerMap.remove(entry.getKey().values().toArray()[0]);
					isMatch = true;
				}
			}
			
			if (!isMatch) {
				unMatchedMap.put(buyer.getKey(), buyer.getValue());
			}
			
		}
		
		unMatchedMap.putAll(sellerMap);
		
		System.out.println("Matched Orders");
		for (Entry<ConcurrentHashMap<String, String>, Integer> map : matchedMap.entrySet()) {
			for(Entry<String, String> valuesMap : map.getKey().entrySet()) {
				System.out.println(valuesMap.getKey() + ":"+valuesMap.getValue());
				matchedList.add(new MatchedOrders(valuesMap.getKey(), valuesMap.getValue()));
			}
		}
		
		System.out.println("UnMatched Orders");
		for(Entry<String, Integer> valuesMap : unMatchedMap.entrySet()) {
				System.out.println(valuesMap.getKey() + ":"+valuesMap.getValue());
				unMatchedOrders.add(new UnMatchedOrders(valuesMap.getKey(), valuesMap.getValue()));
		}
		
		returnList.add((List<T>) matchedList);
		returnList.add((List<T>) unMatchedOrders);
		return returnList;
		
	}
	
}
