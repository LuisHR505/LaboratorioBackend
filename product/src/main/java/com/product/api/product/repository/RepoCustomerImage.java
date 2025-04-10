package com.product.api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.product.entity.CustomerImage;

@Repository
public interface RepoCustomerImage extends JpaRepository<CustomerImage, Integer> {

	@Query(value = "SELECT * FROM customer_image WHERE customer_id = :customer_id;", nativeQuery = true)
	CustomerImage findByCustomerId(Integer customer_id);
}
