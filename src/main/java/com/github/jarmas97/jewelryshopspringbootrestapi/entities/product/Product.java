package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.material.Material;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.Photo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany
    @JoinTable(
            name = "product_material",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;
    private BigDecimal price;
    @ElementCollection
    @CollectionTable(name = "product_photos", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "photo")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "profile_photo_id")
    private Photo profilePhoto;
    private int size;
    private int availableStock;
}