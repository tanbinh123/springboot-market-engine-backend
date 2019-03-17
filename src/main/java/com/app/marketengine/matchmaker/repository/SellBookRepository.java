package com.app.marketengine.matchmaker.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.marketengine.matchmaker.entity.SellBook;

@Repository
@Transactional
public interface SellBookRepository extends JpaRepository<SellBook, Long> {
	
}
