package com.wynh.microservices.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wynh.microservices.product.dto.ProductRequest;
import com.wynh.microservices.product.dto.ProductResponse;
import com.wynh.microservices.product.model.Product;
import com.wynh.microservices.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		
		productRepository.save(product);
		
		log.info("Product created successfully.");
		
		return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
	}

	public List<ProductResponse> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll()
				.stream()
				.map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice()))
				.toList();
	}
	
}
