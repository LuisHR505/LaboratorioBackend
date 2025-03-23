package com.product.api.product.service;

import org.springframework.http.ResponseEntity;
import com.product.api.commons.dto.ApiResponse;
import com.product.api.product.dto.DtoCustomerImageIn;


public interface SvcCustomerImage {

	public ResponseEntity<ApiResponse> uploadCustomerImage(DtoCustomerImageIn in);
}
