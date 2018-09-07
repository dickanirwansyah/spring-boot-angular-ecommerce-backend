package com.ecommerce.spring.demoecommerce.service;

import com.ecommerce.spring.demoecommerce.entity.OrderProduct;
import com.ecommerce.spring.demoecommerce.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Transactional
@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired private OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct create(@NotNull(message = "the products for order cannot be null") @Valid OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
