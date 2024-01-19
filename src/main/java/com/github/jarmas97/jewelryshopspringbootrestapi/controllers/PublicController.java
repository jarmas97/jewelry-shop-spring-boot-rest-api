package com.github.jarmas97.jewelryshopspringbootrestapi.controllers;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductService;
import com.github.jarmas97.jewelryshopspringbootrestapi.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PublicController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping(value = "/products")
    public Iterable<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "/product")
    public Optional<Product> getProductById(@RequestParam("id") Long productID) {
        return productRepository.findById(productID);
    }

//    @PostMapping(value = "/checkAdminToken")
//    public boolean checkAdminToken() {
//
//    }
}
