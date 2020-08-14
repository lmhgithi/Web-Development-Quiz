package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAll();
    Optional<OrderEntity> findByName(String name);
}
