package com.product.api.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoCategoryIn;
import com.product.api.product.entity.Category;

public interface SvcCategory {

	public ResponseEntity<List<Category>> getCategories();
	
	// Practica 5: actualizamos la interfaz del servicio Category
	public ResponseEntity<List<Category>> getActiveCategorys();
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in);
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id);
	public ResponseEntity<ApiResponse> enableCategory(Integer id);
	public ResponseEntity<ApiResponse> disableCategory(Integer id);

}
