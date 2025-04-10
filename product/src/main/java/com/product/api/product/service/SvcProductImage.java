package com.product.api.product.service;

import org.springframework.http.ResponseEntity;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoProductImageIn;

public interface SvcProductImage {
	// declaramos un metodo a implementar en otra clase para
	// actualizar la imagen de un producto (o agregar una nueva si no tiene)
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);
}
