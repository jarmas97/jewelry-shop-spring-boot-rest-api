package com.github.jarmas97.jewelryshopspringbootrestapi.entities.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest
class UserTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    private User user;
    @BeforeEach
    private void setUp() {
        this.user = new User("johnsmith@gmail.com", "password123", "USER");
        testEntityManager.persistAndFlush(user);
    }

    @Test
    public void saveUser() {
        assertThat(user).isNotNull();
    }
    @Test
    public void deleteAllUsers() {
        userRepository.deleteAll();
        assertThat(userRepository.findAll()).isEmpty();
    }

}