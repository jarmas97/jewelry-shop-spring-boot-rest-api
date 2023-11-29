package com.github.jarmas97.jewelryshopspringbootrestapi.entities.order;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String clientName;
}
