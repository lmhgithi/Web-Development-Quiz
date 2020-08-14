package com.thoughtworks.rslist.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.Product;
import com.thoughtworks.rslist.entity.OrderEntity;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.repository.OrderRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    OrderRepository orderRepository;

    @Autowired

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        ProductEntity productEntity = ProductEntity.builder()
                .name("可乐1")
                .price("1")
                .unit("瓶")
                .imgUrl("../images/cola.jpg")
                .build();
        productRepository.save(productEntity);
        ProductEntity productEntity2 = ProductEntity.builder()
                .name("雪碧1")
                .price("1")
                .unit("瓶")
                .imgUrl("../images/spirit.jpg")
                .build();
        productRepository.save(productEntity2);
    }

    @Test
    void shouldGetProduct() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("可乐1")))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].imgUrl", is("../images/cola.jpg")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddProductToOrder() throws Exception {
        int productId = productRepository.findAll().get(0).getProductId();
        mockMvc.perform(post("/product/"+productId))
                .andExpect(status().isOk());

        List<OrderEntity> orders = orderRepository.findAll();
        assertEquals(1, orders.size());
    }

    @Test
    void shouldAddProductToOrderAndOnlyIncreaseQuantity() throws Exception {
        int productId = productRepository.findAll().get(0).getProductId();
        mockMvc.perform(post("/product/"+productId))
                .andExpect(status().isOk());
        mockMvc.perform(post("/product/"+productId))
                .andExpect(status().isOk());
        List<OrderEntity> orders = orderRepository.findAll();
        assertEquals(1, orders.size());
        int quantity = orderRepository.findAll().get(0).getQuantity();
        assertEquals(2, quantity);

    }

    @Test
    void shouldAddProduct() throws Exception {
        Product product = Product.builder()
                .name("雪碧5")
                .price("1")
                .unit("瓶")
                .imgUrl("../images/spirit.jpg")
                .build();
        String requestJson = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/product/").content(requestJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/product"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("雪碧5")))
                .andExpect(jsonPath("$[2].price", is("1")))
                .andExpect(jsonPath("$[2].unit", is("瓶")))
                .andExpect(jsonPath("$[2].imgUrl", is("../images/spirit.jpg")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotAddProductWhenNameExists() throws Exception {
        Product product = Product.builder()
                .name("雪碧1")
                .price("1")
                .unit("瓶")
                .imgUrl("../images/spirit.jpg")
                .build();
        String requestJson = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/product/").content(requestJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}