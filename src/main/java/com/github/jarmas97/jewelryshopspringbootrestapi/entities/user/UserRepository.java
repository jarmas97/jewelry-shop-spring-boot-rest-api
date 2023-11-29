package com.github.jarmas97.jewelryshopspringbootrestapi.entities.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
}
