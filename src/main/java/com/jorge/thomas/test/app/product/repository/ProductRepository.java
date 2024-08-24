package com.jorge.thomas.test.app.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jorge.thomas.test.app.product.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
