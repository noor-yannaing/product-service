package com.wynh.microservices.product.service;

import org.springframework.stereotype.Service;

import com.wynh.microservices.product.dto.ProductRequest;
import com.wynh.microservices.product.model.Product;
import com.wynh.microservices.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		
		productRepository.save(product);
	}
	
}
