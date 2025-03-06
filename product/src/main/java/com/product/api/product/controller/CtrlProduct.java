package com.product.api.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.product.entity.Category;
import com.product.api.product.service.SvcCategory;

//Agregamos Rest Controller para decir que esta clase es un controlador.
//indicamos a traves de que path vamos a llegar al endpoint
@RestController
@RequestMapping("/Category") 
public class CtrlProduct {
	
	@Autowired
	SvcCategory svc;

	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		return svc.getCategories();
	}
	
	private List<Category> getCategoryList(){
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1,"Lentes","L1",1));
		categories.add(new Category(2,"Reloj","R1",1));
		categories.add(new Category(3,"Camisa","C1",0));
		
		return categories;
	}
	
	
	

}
