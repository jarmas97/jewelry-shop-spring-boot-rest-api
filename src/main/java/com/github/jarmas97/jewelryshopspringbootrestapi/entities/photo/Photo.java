package com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.product.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;

@Data
@Table(name = "PHOTOS")
@Entity
public class Photo {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "photo")
    private String data;
}