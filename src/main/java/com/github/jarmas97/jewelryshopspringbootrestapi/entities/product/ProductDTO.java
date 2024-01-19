package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo.PhotoDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private List<Long> materialsIDs;
    private BigDecimal price;
    private List<PhotoDTO> photos;
    private Long profilePhotoIndex;
    private int size;
    private int availableStock;
}
