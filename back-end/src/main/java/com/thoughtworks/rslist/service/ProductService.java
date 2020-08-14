package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.domain.Product;
import com.thoughtworks.rslist.entity.ProductEntity;
import com.thoughtworks.rslist.exception.InvalidParamException;
import com.thoughtworks.rslist.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity addProduct(Product product) {
        if(productRepository.findByName(product.getName()).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        ProductEntity productEntity = ProductEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .unit(product.getUnit())
                .imgUrl(product.getImgUrl())
                .build();
        productRepository.save(productEntity);
        return ResponseEntity.ok().build();
    }
}
