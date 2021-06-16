package com.shop.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("FROM Product WHERE available = :available")
	public List<Product> selectByActivate(@Param("available") Integer available);

	
	@Query("Select p FROM Product p WHERE p.available = :available")
	public Page<Product> selectByActivatePagination(@Param("available") Integer available, Pageable pageable);

	@Query("Select p FROM Product p WHERE p.name like :name and p.available = :available")
	public Page<Product> selectByName(@Param("name") String name, @Param("available") Integer available, Pageable pageable);


}
