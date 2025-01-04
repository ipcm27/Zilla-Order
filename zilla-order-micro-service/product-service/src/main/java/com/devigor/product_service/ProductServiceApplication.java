package com.devigor.product_service;

import com.devigor.product_service.dto.ProductResponse;
import com.devigor.product_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class ProductServiceApplication implements CommandLineRunner {

	@Autowired
	ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception  {
/*		ProductRequest iphone15 = new ProductRequest("Iphone 15", "It's an iPhone", new BigDecimal(1199.00));
		ProductRequest samsungGalaxyS23 = new ProductRequest("Samsung Galaxy S23", "Flagship Samsung phone", new BigDecimal(999.00));
		ProductRequest macbookAir = new ProductRequest("MacBook Air", "Light and powerful laptop", new BigDecimal(1299.00));
		ProductRequest dellXPS = new ProductRequest("Dell XPS 13", "High-performance ultrabook", new BigDecimal(1399.00));
		ProductRequest googlePixel7 = new ProductRequest("Google Pixel 7", "Pure Android experience", new BigDecimal(899.00));

		// Criar os produtos
		productService.createProduct(iphone15);
		productService.createProduct(samsungGalaxyS23);
		productService.createProduct(macbookAir);
		productService.createProduct(dellXPS);
		productService.createProduct(googlePixel7);*/

		logger.info("Customers found with findAll():");
		logger.info("-------------------------------");
		for (ProductResponse productResponse : productService.getAllProducts()) {
			logger.info("Product name: {}", productResponse.getName());
			logger.info("Product Price`: {}", productResponse.getPrice());
		}
	}

}
