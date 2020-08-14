package com.thoughtworks.rslist.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.Product;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;

    @Autowired

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
    }

    @Test
    void shouldGetProduct() throws Exception {
        ProductEntity productEntity = ProductEntity.builder()
                .name("可乐1")
                .quantity(1)
                .price(1)
                .unit("瓶")
                .imgUrl("../images/cola.jpg")
                .build();
        productRepository.save(productEntity);
        ProductEntity productEntity2 = ProductEntity.builder()
                .name("雪碧1")
                .quantity(1)
                .price(1)
                .unit("瓶")
                .imgUrl("../images/spirit.jpg")
                .build();
        productRepository.save(productEntity2);
        mockMvc.perform(get("/product"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("可乐1")))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].imgUrl", is("../images/cola.jpg")))
                .andExpect(status().isOk());
    }
}