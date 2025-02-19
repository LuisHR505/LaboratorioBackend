package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Agregamos Rest Controller para decir que esta clase es un controlador.
//indicamos a traves de que path vamos a llegar al endpoint
@RestController
@RequestMapping("/helloworld") 
public class CtrlProduct {

	//Implementamos el endpoint, indicando que llegamos a el por medio de un metodo GET
	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}
	

}
