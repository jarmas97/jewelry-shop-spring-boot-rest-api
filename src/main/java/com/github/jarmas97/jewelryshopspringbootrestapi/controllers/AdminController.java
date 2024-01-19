package com.github.jarmas97.jewelryshopspringbootrestapi.controllers;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.MaterialRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductDTO;
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
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PostMapping("/change-product")
    public ResponseEntity<String> changeProduct(@RequestBody ProductDTO productDTO) {
        return productService.changeProduct(productDTO);
    }

    @DeleteMapping("/remove-product")
    public ResponseEntity<String> removeProduct(@RequestParam("productId") Long productID) {
        return productService.removeProduct(productID);
    }

    @GetMapping("/materials")
    public Iterable<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    @GetMapping("/add-material")
    public ResponseEntity<String> addMaterial(@RequestParam("materialName") String materialName) {
        System.out.println(materialName.toUpperCase());
        Material material = new Material();
        material.setName(materialName.toUpperCase());
        materialRepository.save(material);
        return ResponseEntity.ok("Added material = " + materialName);
    }
}