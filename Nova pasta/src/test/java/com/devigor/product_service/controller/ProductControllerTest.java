package com.devigor.product_service.controller;

import com.devigor.product_service.dto.ProductRequest;
import com.devigor.product_service.model.Product;
import com.devigor.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String API_URL = "/api/controller";
    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void cleanDatabase(){
        productRepository.deleteAll();
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Iphone 10")
                .price(new BigDecimal(599.00))
                .description("It's an iPhone 10")
                .build();
    }
    private Product createAndSaveProduct(){
        Product product = Product.builder()
                .name("Iphone 10")
                .price(new BigDecimal(599.00))
                .description("It's an iPhone 10")
                .build();
        return productRepository.save(product);
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product savedProduct = createAndSaveProduct();

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL + "/products/" + savedProduct.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Iphone 10"))
                .andExpect(jsonPath("$.price").value(599.00))
                .andExpect(jsonPath("$.description").value("It's an iPhone 10"));
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        createAndSaveProduct();

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL + "/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Iphone 10"))
                .andExpect(jsonPath("$[0].price").value(599.00))
                .andExpect(jsonPath("$[0].description").value("It's an iPhone 10"));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        Product savedProduct = createAndSaveProduct();

        ProductRequest updatedRequest = ProductRequest.builder()
                .name("Iphone 11")
                .price(new BigDecimal(699.00))
                .description("Updated iPhone 11")
                .build();

        String productRequestSTR = objectMapper.writeValueAsString(updatedRequest);


        mockMvc.perform(MockMvcRequestBuilders.put(API_URL + "/products/" + savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestSTR))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Iphone 11"))
                .andExpect(jsonPath("$.price").value(699.00))
                .andExpect(jsonPath("$.description").value("Updated iPhone 11"));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestSTR = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestSTR))
                .andExpect(status().isCreated());

        Assertions.assertTrue(productRepository.findAll().size() == 1);
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Product savedProduct = createAndSaveProduct();

        mockMvc.perform(MockMvcRequestBuilders.delete(API_URL + "/"+ savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        Assertions.assertFalse(deletedProduct.isPresent());

    }



}