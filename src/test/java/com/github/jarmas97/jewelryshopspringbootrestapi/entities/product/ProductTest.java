//package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class ProductTest {
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private ProductRepository productRepository;
//    private Product product;
//    @BeforeEach
//    public void setUp() {
//        this.product = new Product(
//                "golden ring 1",
//                "this is a golden ring with diamond",
//                Category.RING,
//                Arrays.asList(Material.GOLD, Material.DIAMOND),
//                BigDecimal.valueOf(350.45)
//        );
//        testEntityManager.persistAndFlush(product);
//    }
//    @Test
//    public void saveProduct() {
//        assertThat(product).isNotNull();
//    }
//    @Test
//    public void deleteAllProducts() {
//        productRepository.deleteAll();
//        assertThat(productRepository.findAll()).isEmpty();
//    }
//}