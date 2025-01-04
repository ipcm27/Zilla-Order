package com.devigor.product_service.service;

import com.devigor.product_service.dto.ProductRequest;
import com.devigor.product_service.dto.ProductResponse;
import com.devigor.product_service.exceptions.ProductNotFoundException;
import com.devigor.product_service.model.Product;
import com.devigor.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final MongoTemplate mongoTemplate;


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info( "Product {} created successfully", product.getName());
    }

    public Product getProduct(String id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
    }

    public void deleteProduct(String id) {
        productRepository.deleteProductById(id);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("name",productRequest.getName());
        update.set("description",productRequest.getDescription());
        update.set("price", productRequest.getPrice());

        FindAndModifyOptions options = new FindAndModifyOptions()
                .returnNew(true)
                .upsert(false);
        Product updatedProduct = mongoTemplate.findAndModify(query, update, options, Product.class);

        if (updatedProduct == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }

        return mapToProductResponse(updatedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return  products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
