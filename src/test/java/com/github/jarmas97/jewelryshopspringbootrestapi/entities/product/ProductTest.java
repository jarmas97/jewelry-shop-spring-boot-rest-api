package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.MaterialRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.Photo;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@DataJpaTest
@ActiveProfiles("test")
public class ProductTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Test
    public void testSaveProductWithBase64Photos() throws IOException {
        // Arrange
        Material material = new Material();
        material.setName("Gold");
        materialRepository.save(material);

        Photo profilePhoto = new Photo();
        profilePhoto.setPhoto(loadPhoto("classpath:base64photos/base64encodedprofilephoto.txt"));

        Photo photo1 = new Photo();
        photo1.setPhoto(loadPhoto("classpath:base64photos/base64encodedphoto1.txt"));

        Photo photo2 = new Photo();
        photo2.setPhoto(loadPhoto("classpath:base64photos/base64encodedphoto2.txt"));

        Product product = new Product();
        product.setName("Diamond Necklace");
        product.setDescription("Beautiful necklace with diamonds");
        product.setCategory(Category.NECKLACE);
        product.setMaterials(Arrays.asList(material));
        product.setPrice(new BigDecimal("1000.0"));
        product.setProfilePhoto(profilePhoto);
        product.setPhotos(Arrays.asList(photo1, photo2));

        // Act
        Product savedProduct = testEntityManager.persistFlushFind(product);

        // Assert
        assertNotNull(savedProduct.getId());
        assertEquals("Diamond Necklace", savedProduct.getName());
        assertEquals("Beautiful necklace with diamonds", savedProduct.getDescription());
        assertEquals(Category.NECKLACE, savedProduct.getCategory());
        assertEquals(1, savedProduct.getMaterials().size());
        assertEquals(material, savedProduct.getMaterials().get(0));
        assertEquals(new BigDecimal("1000.0"), savedProduct.getPrice());
        assertNotNull(savedProduct.getProfilePhoto());
        assertNotNull(savedProduct.getProfilePhoto().getId());
        assertNotNull(savedProduct.getProfilePhoto().getPhoto());

        // Dodatkowe sprawdzenie dla PhotoRepository
        Photo savedProfilePhoto = photoRepository.findById(savedProduct.getProfilePhoto().getId()).orElse(null);
        assertNotNull(savedProfilePhoto);
        assertNotNull(savedProfilePhoto.getPhoto());

        for (Photo savedPhoto : savedProduct.getPhotos()) {
            Photo retrievedPhoto = photoRepository.findById(savedPhoto.getId()).orElse(null);
            assertNotNull(retrievedPhoto);
            assertNotNull(retrievedPhoto.getPhoto());
        }
    }

    private String loadPhoto(String path) throws IOException {
        Path photoPath = Paths.get(path);
        byte[] bytes = Files.readAllBytes(photoPath);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}