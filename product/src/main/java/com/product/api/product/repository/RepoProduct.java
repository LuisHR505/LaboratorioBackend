package com.product.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.product.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {
	@Query(value = "SELECT * FROM customer_image WHERE product_id = :product_id;", nativeQuery = true)
	Product findByProductId(Integer product_id);

}
