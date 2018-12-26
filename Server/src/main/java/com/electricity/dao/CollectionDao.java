package com.electricity.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.electricity.model.Collection;




public interface CollectionDao extends JpaRepository<Collection, Integer>{
	List<Collection> findByUsername(String username);
	
	@Query(nativeQuery=true,value="select * FROM collection  WHERE username=:username and type=:type and product_id=:product_id ORDER BY id DESC")
	Collection findByAll(@Param("username") String username,@Param("type") String type,@Param("product_id") int product_id);
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="delete FROM collection  WHERE username=?1 and type=?2 and product_id=?3")
	void deleteByAll(@Param("username") String username,@Param("type") String type,@Param("product_id") int product_id);
	


}
