package com.product.api.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoCategoryIn;
import com.product.api.product.entity.Category;
import com.product.api.product.service.SvcCategory;
import com.product.exception.ApiException;

import jakarta.validation.Valid;

//Agregamos Rest Controller para decir que esta clase es un controlador.
//indicamos a traves de que path vamos a llegar al endpoint
@RestController
@RequestMapping("/Category")
public class CtrlProduct {

	@Autowired
	SvcCategory svc;

	@GetMapping
	public ResponseEntity<List<Category>> getCategories() {
		return svc.getCategories();
	}
	
	private List<Category> getCategoryList() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1, "Lentes", "L1", 1));
		categories.add(new Category(2, "Reloj", "R1", 1));
		categories.add(new Category(3, "Camisa", "C1", 0));

		return categories;
	}
	
	//declaramos el controlador para poder regresar al usuario una categoria por id. 
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Integer id) {
		return svc.getCategory(id);
	}

	// declaramos los nuevos metodos de la interfaz del servicio en el controlador.
	@GetMapping("/active")
	public ResponseEntity<List<Category>> getActiveCategorys() {
		return svc.getActiveCategories();
	}

	@PostMapping
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody DtoCategoryIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		return svc.createCategory(in);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Integer id,
			@Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return svc.updateCategory(in, id);
	}

	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableCategory(@PathVariable("id") Integer id) {
		return svc.enableCategory(id);
	}

	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableCategory(@PathVariable("id") Integer id) {
		return svc.disableCategory(id);
	}

}
