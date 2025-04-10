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
import com.product.api.product.dto.DtoCustomerImageIn;
import com.product.api.product.service.SvcCustomerImage;
import com.product.exception.ApiException;

import jakarta.validation.Valid;

//declaramos que esta clase sera un controlador
//declaramos el path
@RestController
@RequestMapping("/customer-image")
public class CtrlCustomerImage {

	// declaramos un bean del servicio customer image.
	@Autowired
	SvcCustomerImage svc;

	// declaramos un metodo POST para poder actualizar la imagen de un customer.
	@PostMapping
	public ResponseEntity<ApiResponse> createCustomerImage(@Valid @RequestBody DtoCustomerImageIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.uploadCustomerImage(in);
	}

}
