package com.product.api.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.product.entity.Category;
import com.product.api.product.repository.RepoCategory;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {
	@Autowired
	RepoCategory repo;

	@Override
	public ResponseEntity<List<Category>> getCategories() {
		try {
			return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);

		} catch (Exception DataAccessException) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "No se encontraron las categorias en la base de datos");
		}

	}

}
