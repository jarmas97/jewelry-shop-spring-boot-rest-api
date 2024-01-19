package com.github.jarmas97.jewelryshopspringbootrestapi.entities.product;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
