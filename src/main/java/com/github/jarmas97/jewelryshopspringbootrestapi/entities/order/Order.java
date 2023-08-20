package com.github.jarmas97.jewelryshopspringbootrestapi.entities.order;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String clientName;

    public Order() {
    }

    public Order(List<Product> products, Status status, String clientName) {
        this.products = products;
        this.status = status;
        this.clientName = clientName;
    }

    public long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(products, order.products) && status == order.status && Objects.equals(clientName, order.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, status, clientName);
    }
}
