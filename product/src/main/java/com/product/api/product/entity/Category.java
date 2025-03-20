package com.product.api.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {
	// agregamos los decoradores necesarios
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("category_id")
	@Column(name = "category_id")
	private Integer category_id = 0;
	
	@JsonProperty("category")
	@Column(name = "category")
	private String category = "";
	
	@JsonProperty("tag")
	@Column(name = "tag")
	private String tag = "";
	
	@JsonProperty("status")
	@Column(name = "status")
	private Integer status = 0;

	public Category(Integer category_id, String category, String tag, Integer status) {
		this.category_id = category_id;
		this.category = category;
		this.tag = tag;
		this.status = status;
	}

	public Category() {
		// Este constructor es necesario para JPA
	}

	// metodos get
	public int getId() {
		return this.category_id;
	}

	public String getCategoryName() {
		return this.category;
	}

	public String getTag() {
		return this.tag;
	}

	public int getStatus() {
		return this.status;
	}

	// metodos set
	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCategoryId(Integer category_id) {
		this.category_id = category_id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "{" + category_id.toString() + "," + category + "," + tag + "," + status + "}";
	}

}
