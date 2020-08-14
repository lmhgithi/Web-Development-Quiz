package com.thoughtworks.rslist.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.entity.OrderEntity;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.repository.OrderRepository;
import com.thoughtworks.rslist.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
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
        OrderEntity orderEntity = OrderEntity.builder()
                .name("可乐1")
                .productId(1)
                .quantity(1)
                .price(1)
                .unit("瓶")
                .build();
        orderRepository.save(orderEntity);
        OrderEntity orderEntity2 = OrderEntity.builder()
                .name("雪碧1")
                .productId(2)
                .quantity(1)
                .price(1)
                .unit("瓶")
                .build();
        orderRepository.save(orderEntity2);
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
    }

    @Test
    void shouldGetOrder() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("可乐1")))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        int orderIdToDelete = orderRepository.findAll().get(0).getOrderId();
        mockMvc.perform(delete("/order/"+orderIdToDelete))
                .andExpect(status().isOk());
        assertEquals(1, orderRepository.findAll().size());
    }

}