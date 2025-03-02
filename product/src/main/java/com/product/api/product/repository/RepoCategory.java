package com.product.api.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.product.entity.Category;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{
	@Query(value ="SELECT * FROM Category ORDER BY category", nativeQuery = true)
	List<Category> getCategory();

}
