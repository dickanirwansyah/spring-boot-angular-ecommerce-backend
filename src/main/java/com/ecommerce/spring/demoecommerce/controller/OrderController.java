package com.ecommerce.spring.demoecommerce.controller;

import com.ecommerce.spring.demoecommerce.dto.OrderProductDto;
import com.ecommerce.spring.demoecommerce.entity.Order;
import com.ecommerce.spring.demoecommerce.entity.OrderProduct;
import com.ecommerce.spring.demoecommerce.entity.OrderStatus;
import com.ecommerce.spring.demoecommerce.exception.ResourceNotFoundException;
import com.ecommerce.spring.demoecommerce.service.OrderProductService;
import com.ecommerce.spring.demoecommerce.service.OrderService;
import com.ecommerce.spring.demoecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired private ProductService productService;

    @Autowired private OrderService orderService;

    @Autowired private OrderProductService orderProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }


    private void validateProductExistence(List<OrderProductDto> orderProductDtos){
        List<OrderProductDto> list = orderProductDtos
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(
                        op.getProduct()
                                .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)){
            new ResourceNotFoundException("product not found");
        }
    }

    private static class OrderForm{

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders(){
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders){
            this.productOrders = productOrders;
        }
    }
}
