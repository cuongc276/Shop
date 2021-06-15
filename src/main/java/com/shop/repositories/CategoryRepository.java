package com.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query("SELECT e FROM Category e WHERE e.name= :name")
	public Category getCategoryByName(@Param("name") String name);

	@Query("SELECT e FROM Category e WHERE e.name = :name AND e.id <> :id")
	public Category searchCategory(@Param("name") String name, @Param("id") Integer id);

}
