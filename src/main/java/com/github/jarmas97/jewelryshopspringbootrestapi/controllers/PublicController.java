package com.github.jarmas97.jewelryshopspringbootrestapi.controllers;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.order.OrderService;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductService;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/products")
    public Iterable<Product> getAllProducts() {
        return productService.findAll();
    }

}
