package com.ecommerce.spring.demoecommerce.repository;

import com.ecommerce.spring.demoecommerce.entity.OrderProduct;
import com.ecommerce.spring.demoecommerce.entity.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK>{
}
