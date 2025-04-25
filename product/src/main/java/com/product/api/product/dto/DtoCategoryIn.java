package com.product.api.product.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoCategoryIn {
	@JsonProperty("region")
	@NotNull(message = "La región es obligatoria")
	private String category;

	@JsonProperty("tag")
	@NotNull(message = "El tag es obligatorio")
	private String tag;
	
	//setters y getters 
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
