package com.github.jarmas97.jewelryshopspringbootrestapi.entities.material;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
@Data
@Entity
@Table(name = "MATERIALS")
public class Material {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
