package com.app.marketengine.matchmaker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.app.marketengine.matchmaker.entity.BuyBook;
import com.app.marketengine.matchmaker.entity.MatchedOrders;
import com.app.marketengine.matchmaker.entity.SellBook;
import com.app.marketengine.matchmaker.entity.UnMatchedOrders;

@Service
public class MatchingExecutorService {

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
					matchedList.add(new MatchedOrders(b.getUserID(), set.getKey(), set.getValue()));
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
	
}
