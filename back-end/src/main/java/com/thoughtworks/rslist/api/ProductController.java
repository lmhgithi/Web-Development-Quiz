package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.service.OrderService;
import com.thoughtworks.rslist.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    private final OrderService orderService;

    public ProductController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/product")
    @CrossOrigin
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/product/{id}")
    @CrossOrigin
    public ResponseEntity addProductToOrder(@PathVariable int id) {
        return orderService.addProductToOrder(id);
    }

}
