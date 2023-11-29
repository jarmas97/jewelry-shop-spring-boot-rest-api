package com.github.jarmas97.jewelryshopspringbootrestapi.entities.photo;

import lombok.Data;

@Data
public class PhotoDTO {
    private Long id;
    private String photo;
    private boolean isProfile;
}
