package com.electricity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.electricity.model.Manager;



/**
 * hibernate需要集成的  与spring data jpa 集成的 JpaRepository非常类似
 * 所以基本可以说 使用spring boot框架最好使用spring data jpa持久化框架，不用hibernate。
 * */
public interface ManagerDao  extends JpaRepository<Manager, Integer>{
	
	
	Manager findByUsername(String username);
//	Manager findByNameAndAge(String username, Integer age);
//	@Query("from Manager u where u.name=:name")
//	Manager findUser(@Param("name") String name);
	
}
