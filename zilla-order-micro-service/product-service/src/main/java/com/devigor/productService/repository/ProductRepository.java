package com.devigor.productService.repository;

import com.devigor.productService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


    public Optional<Product> findProductById(String id);
    public void deleteProductById(String id);
}
