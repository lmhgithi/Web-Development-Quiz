package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.entity.OrderEntity;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.repository.OrderRepository;
import com.thoughtworks.rslist.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
            Optional<OrderEntity> orderEntityExist = orderRepository.findByName(productEntity.get().getName());
            if (orderEntityExist.isPresent()) {
                orderEntityExist.get().setQuantity(orderEntityExist.get().getQuantity() + 1);
                orderRepository.save(orderEntityExist.get());
            } else {
                OrderEntity orderEntityNewCreate = OrderEntity.builder()
                        .productId(id)
                        .name(productEntity.get().getName())
                        .quantity(1)
                        .price(productEntity.get().getPrice())
                        .unit(productEntity.get().getUnit())
                        .build();
                orderRepository.save(orderEntityNewCreate);
            }

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    public ResponseEntity deleteOrderById(int id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
