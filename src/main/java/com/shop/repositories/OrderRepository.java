package com.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.entity.Order;
import com.shop.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("SELECT entity FROM Order entity WHERE entity.user.id=:id")
	public List<Order> searchByIDUser(@Param("id") Integer idUser);
	
	@Query("SELECT e FROM Order e WHERE e.create_date = :create_date AND e.user = :user AND e.address = :address")
	public Order getOrderByFiled(@Param("create_date") String create_date, @Param("user") User user, @Param("address") String address);
	
}
