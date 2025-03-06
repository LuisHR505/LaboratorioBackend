package com.product.api.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.product.entity.Category;

public interface SvcCategory {
	
	public ResponseEntity<List<Category>> getCategories();
}
