package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ElementCollection(targetClass = Material.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_materials",
            joinColumns = @JoinColumn(name = "product_id"))
    private List<Material> materials;
    private BigDecimal price;

    public Product() {
    }
    public Product(String name, String description, Category category, List<Material> materials, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.materials = materials;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && category == product.category && Objects.equals(materials, product.materials) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, materials, price);
    }
}
