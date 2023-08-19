package com.github.jarmas97.jewelryshopspringbootrestapi.entities.order;

import static org.assertj.core.api.Assertions.assertThat;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Category;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
@RunWith(SpringRunner.class)
@DataJpaTest
class OrderTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private OrderRepository orderRepository;
    private Product product1;
    private Product product2;
    private Order order1;

    @BeforeEach
    public void setUp() {
        this.product1 = new Product(
                "golden ring 1",
                "this is a golden ring with diamond",
                Category.RING,
                Arrays.asList(Material.GOLD, Material.DIAMOND),
                BigDecimal.valueOf(350.45)
        );

        this.product2 = new Product(
                "golden ring 2",
                "this is a golden ring with emerald",
                Category.RING,
                Arrays.asList(Material.GOLD, Material.EMERALD),
                BigDecimal.valueOf(220.85)
        );

        List<Product> products = Arrays.asList(product1, product2);
        this.order1 = new Order(products, Status.PENDING, "John Doe");

        testEntityManager.persistAndFlush(product1);
        testEntityManager.persistAndFlush(product2);
        testEntityManager.persistAndFlush(order1);
    }

    @Test
    public void saveOrder() {
        assertThat(order1).isNotNull();
    }

    @Test
    public void deleteOrder() {
        orderRepository.deleteAll();
        assertThat(orderRepository.findAll()).isEmpty();
    }

}