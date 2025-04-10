package com.product.api.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoProductImageIn;
import com.product.api.product.service.SvcProductImage;
import com.product.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-image")
public class CtrlProductImage {

	// declaramos un bean del servicio product image
	@Autowired
	SvcProductImage svc;

	@PostMapping
	public ResponseEntity<ApiResponse> createProductImage(@Valid @RequestBody DtoProductImageIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.uploadProductImage(in);
	}

}
