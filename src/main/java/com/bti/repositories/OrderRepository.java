package com.bti.repositories;

import com.bti.model.Order;

import java.util.Optional;

public interface OrderRepository extends AbstractRepository<Order, Long> {
    Optional<Order> findByOrderReference(String orderReference);

}