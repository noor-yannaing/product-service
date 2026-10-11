package com.wynh.microservices.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wynh.microservices.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
