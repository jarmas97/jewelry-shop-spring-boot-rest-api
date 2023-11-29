package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.MaterialRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private PhotoRepository photoRepository;
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
    //    @Transactional
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {

        System.out.println(productRequest);

//        Product newProduct = new Product();
//        newProduct.setName(name);
//        newProduct.setDescription(description);
//        newProduct.setCategory(Category.valueOf(category));
//        newProduct.setMaterials(new ArrayList<>());
//
//        for (Long materialId : materialIds) {
//            Optional<Material> materialOptional = materialRepository.findById(materialId);
//            materialOptional.ifPresent(newProduct.getMaterials()::add);
//        }
//        newProduct.setPrice(price);
//        List<Photo> photoList = new ArrayList<>();
//
//        for (PhotoDTO photoDTO : photos) {
//            if (photoDTO.isProfile()) {
//                Photo profilePhoto = new Photo();
//                profilePhoto.setPhoto(photoDTO.getPhoto());
//                photoRepository.save(profilePhoto);
//                newProduct.setProfilePhoto(profilePhoto);
//            } else {
//                Photo photo = new Photo();
//                photo.setPhoto(photoDTO.getPhoto());
//                photoRepository.save(photo);
//                photoList.add(photo);
//            }
//        }
//
//        newProduct.setPhotos(photoList);
//
//        productRepository.save(newProduct);

        return ResponseEntity.ok("Product added successfully");
    }

    public ResponseEntity<String> removeProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // removing connected materials and photos
            product.setMaterials(null);
            product.setPhotos(null);
            // removing product
            System.out.println("removing product = " + productId);
            productRepository.delete(product);

            return new ResponseEntity<>("Product successfully removed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}
