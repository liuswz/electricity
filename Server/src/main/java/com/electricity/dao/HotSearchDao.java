package com.electricity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electricity.model.HotSearch;
import com.electricity.model.Manager;



public interface HotSearchDao extends JpaRepository<HotSearch, Integer>{
	HotSearch findByValue(String value);
}
