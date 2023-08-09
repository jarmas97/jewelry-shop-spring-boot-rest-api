package com.github.jarmas97.jewelryshopspringbootrestapi;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Category;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Material;
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

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Product product1 = new Product(
					"gold ring with diamonds",
					"description...something",
					Category.RING,
					Arrays.asList(Material.GOLD, Material.DIAMOND),
					BigDecimal.valueOf(240)
			);
			productRepository.save(product1);
		};
	}

}
