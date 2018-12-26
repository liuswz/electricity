package com.electricity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electricity.model.Advertisement;



public interface AdvertisementDao extends JpaRepository<Advertisement, Integer>{
	
	Advertisement findByPageId(int pageId);
	List<Advertisement> findByType(int type);
	
}