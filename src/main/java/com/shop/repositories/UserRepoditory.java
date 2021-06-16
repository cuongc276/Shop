package com.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.entity.User;

@Repository
public interface UserRepoditory extends JpaRepository<User, Integer>{
	@Query("SELECT entity FROM User entity WHERE entity.email=:email")
	public User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT entity FROM User entity WHERE entity.email=:email AND entity.id <> :id")
	public User searchByName(@Param("email") String email, @Param("id") Integer id);
	
	@Query("FROM User WHERE activate = :activate")
	public List<User> selectByActivate(@Param("activate") Integer activate);
	
	@Query("SELECT entity FROM User entity WHERE entity.name LIKE :name")
	public List<User> findAllByName(@Param("name") String name);
	
	
}
