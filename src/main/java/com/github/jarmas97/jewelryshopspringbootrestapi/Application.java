package com.github.jarmas97.jewelryshopspringbootrestapi;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Category;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class Application {

	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
