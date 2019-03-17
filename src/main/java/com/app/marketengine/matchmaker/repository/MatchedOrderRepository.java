package com.app.marketengine.matchmaker.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.app.marketengine.matchmaker.entity.BuyBook;

@Repository
@Transactional
public interface MatchedOrderRepository extends JpaRepository<BuyBook, Long> {
	
}
