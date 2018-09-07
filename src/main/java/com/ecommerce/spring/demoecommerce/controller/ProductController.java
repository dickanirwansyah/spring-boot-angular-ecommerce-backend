package com.ecommerce.spring.demoecommerce.controller;

import com.ecommerce.spring.demoecommerce.entity.Product;
import com.ecommerce.spring.demoecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping(value = {"", "/"})
    public @NotNull Iterable<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
