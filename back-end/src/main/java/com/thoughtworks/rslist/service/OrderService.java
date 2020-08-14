package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.entity.OrderEntity;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.repository.OrderRepository;
import com.thoughtworks.rslist.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    public ResponseEntity addProductToOrder(int id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            OrderEntity orderEntity = OrderEntity.builder()
                    .productId(id)
                    .name(productEntity.get().getName())
                    .quantity(5)
                    .price(productEntity.get().getPrice())
                    .unit(productEntity.get().getUnit())
                    .build();
            orderRepository.save(orderEntity);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
