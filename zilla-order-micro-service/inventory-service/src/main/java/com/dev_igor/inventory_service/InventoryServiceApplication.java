package com.dev_igor.inventory_service;

import com.dev_igor.inventory_service.model.Inventory;
import com.dev_igor.inventory_service.repository.InventoryRepository;
import com.dev_igor.inventory_service.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
