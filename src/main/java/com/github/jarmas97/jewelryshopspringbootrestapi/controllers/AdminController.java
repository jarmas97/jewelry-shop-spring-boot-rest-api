package com.github.jarmas97.jewelryshopspringbootrestapi.controllers;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.MaterialRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductRequest;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private MaterialRepository materialRepository;

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }
    @DeleteMapping("/remove-product")
    public ResponseEntity<String> removeProduct(@RequestParam("productId") Long productId) {
        return productService.removeProduct(productId);
    }
    @GetMapping(value = "/materials")
    public Iterable<Material> findAllMaterials() {
        return materialRepository.findAll();
    }
}