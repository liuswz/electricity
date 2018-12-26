package com.electricity.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.electricity.model.User;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	User findById(int id);
}
