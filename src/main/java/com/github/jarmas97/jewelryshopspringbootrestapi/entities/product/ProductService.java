package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.MaterialRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.Photo;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoDTO;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private PhotoRepository photoRepository;
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }
    @Transactional
    public ResponseEntity<String> addProduct(ProductDTO productDTO) {

        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setCategory(Category.valueOf(productDTO.getCategory()));
        newProduct.setMaterials(new ArrayList<>());

        for (Long materialId : productDTO.getMaterialsIDs()) {
            Optional<Material> materialOptional = materialRepository.findById(materialId);
            materialOptional.ifPresent(newProduct.getMaterials()::add);
        }
        newProduct.setPrice(productDTO.getPrice());
        List<PhotoDTO> photoDTOList = productDTO.getPhotos();
        List<Photo> photoList = new ArrayList<>();

        for (int i = 0; i < photoDTOList.size(); i++) {
            Photo photo = new Photo();
            photo.setData(photoDTOList.get(i).getData());
            photoRepository.save(photo);
            photoList.add(photo);
            if (i == productDTO.getProfilePhotoIndex()) {
                newProduct.setProfilePhoto(photo);
            }
        }
        newProduct.setPhotos(photoList);
        newProduct.setSize(productDTO.getSize());
        newProduct.setAvailableStock(productDTO.getAvailableStock());
        productRepository.save(newProduct);

        return ResponseEntity.ok("Product added successfully");
    }

    @Transactional
    public ResponseEntity<String> changeProduct(ProductDTO productDTO) {
        removeProduct(productDTO.getId());
        addProduct(productDTO);
        return ResponseEntity.ok("Product changed successfully");
    }

    @Transactional
    public ResponseEntity<String> removeProduct(Long productID) {
        Optional<Product> productOptional = productRepository.findById(productID);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            for (Photo photo : product.getPhotos()) {
                photoRepository.deleteById(photo.getId());
            }
            productRepository.delete(product);
        }
        return ResponseEntity.ok("Product deleted successfully");
    }
}
