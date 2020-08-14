package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.entity.OrderEntity;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.service.OrderService;
import com.thoughtworks.rslist.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/order")
    @CrossOrigin
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/order/{id}")
    @CrossOrigin
    public ResponseEntity<List<OrderEntity>> deleteOrderById(@PathVariable int id) {
        return orderService.deleteOrderById(id);
    }

}
