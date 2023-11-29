package com.github.jarmas97.jewelryshopspringbootrestapi.entities.order;

import static org.assertj.core.api.Assertions.assertThat;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
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
        this.product1 = new Product();
        this.product2 = new Product();

        List<Product> products = Arrays.asList(product1, product2);
        this.order1 = new Order();

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