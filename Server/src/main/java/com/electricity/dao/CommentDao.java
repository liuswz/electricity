package com.electricity.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.electricity.model.Comment;



public interface CommentDao  extends JpaRepository<Comment, Integer>{
	
	Comment findById(int id);
	
	@Query(nativeQuery=true,value="select * FROM comment WHERE productId =?1 and type=?2 ORDER BY id DESC")
	List<Comment> findByProductId(@Param("productId") int productId,@Param("type") String type,Pageable pageable);
	
	@Query(nativeQuery=true,value="select * FROM comment WHERE username=?1 and type=?2 ORDER BY id DESC")
	List<Comment> findByUsername(@Param("username") String username,@Param("type") String type,Pageable pageable);

}
