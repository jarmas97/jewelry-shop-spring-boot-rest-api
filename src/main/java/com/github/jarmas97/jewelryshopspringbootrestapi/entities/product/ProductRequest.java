package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private List<Long> materials;
    private BigDecimal price;
    private List<PhotoDTO> photos;
}
