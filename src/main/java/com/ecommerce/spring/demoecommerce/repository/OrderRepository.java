package com.ecommerce.spring.demoecommerce.repository;

import com.ecommerce.spring.demoecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
