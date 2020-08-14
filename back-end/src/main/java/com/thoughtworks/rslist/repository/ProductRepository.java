package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findById(Integer id);
}
