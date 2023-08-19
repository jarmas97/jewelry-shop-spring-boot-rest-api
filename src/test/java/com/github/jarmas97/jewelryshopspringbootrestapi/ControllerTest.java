package com.github.jarmas97.jewelryshopspringbootrestapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testResponseContainsProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    void testProductFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].materials").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").exists());
    }
}