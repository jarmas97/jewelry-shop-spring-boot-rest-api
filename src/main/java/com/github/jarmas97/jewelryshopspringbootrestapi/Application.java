package com.github.jarmas97.jewelryshopspringbootrestapi;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.MaterialRepository;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoDTO;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductDTO;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.ProductService;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.user.User;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SpringBootApplication
public class Application {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private ProductService productService;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//
//			// admin Comertys234
//			User user = new User();
//			user.setUsername("admin");
//			user.setPassword("$2a$12$oQdlfcRpRJxbLH4vG7/tf.X3ZiGdd2CvWvWE0qxGt.ldTp/u5THUW");
//			user.setRole("ADMIN");
//			userRepository.save(user);
//
//			//materials
//			Material material = new Material();
//			material.setName("SODALIT");
//			materialRepository.save(material);
//			Material material1 = new Material();
//			material1.setName("HEMATYT");
//			materialRepository.save(material1);
//			Material material2 = new Material();
//			material2.setName("PERŁA");
//			materialRepository.save(material2);
//			Material material3 = new Material();
//			material3.setName("SPINEL");
//			materialRepository.save(material3);
//
//			//default products
//
//			ProductDTO productDTO = new ProductDTO();
//
//			productDTO.setName("Naszyjnik Blue Sky");
//			productDTO.setDescription("Opis...");
//			productDTO.setCategory("NECKLACE");
//			productDTO.setSize(40);
//			productDTO.setAvailableStock(1);
//			List<Long> materialIDs = Arrays.asList(1L,2L,3L);
//			productDTO.setMaterialsIDs(materialIDs);
//			productDTO.setProfilePhotoIndex(1L);
//			productDTO.setPrice(BigDecimal.valueOf(120));
//
//			PhotoDTO photoDTO = new PhotoDTO();
//			photoDTO.setData("afadfadf");
//
//			PhotoDTO photoDTO1 = new PhotoDTO();
//			photoDTO1.setData("afadfsdasdadf");
//
//			List<PhotoDTO> photos = Arrays.asList(photoDTO1, photoDTO);
//			productDTO.setPhotos(photos);
//
//			productService.addProduct(productDTO);
//
//		};
//	}

}
