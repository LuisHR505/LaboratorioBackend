package com.product.api.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.product.entity.Category;
import com.product.api.product.repository.RepoCategory;

@Service
public class SvcCategoryImp implements SvcCategory{
	@Autowired
	RepoCategory repo;
	
	@Override
	public List<Category> getRegions() {
		return repo.getCategory();
	}

	

}
