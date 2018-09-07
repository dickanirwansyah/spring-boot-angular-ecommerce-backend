package com.ecommerce.spring.demoecommerce;

import com.ecommerce.spring.demoecommerce.entity.Product;
import com.ecommerce.spring.demoecommerce.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoEcommerceApplication.class, args);
	}

	/**
	@Bean
	CommandLineRunner runner(ProductService productService){

		return args -> {
			productService.save(new Product(1L, "Tv Lcd Samsung", 500.000, "http://placehold.it/200x100"));
			productService.save(new Product(2L, "Iphone", 400.000, "http://placehold.it/200x100"));
			productService.save(new Product(3L, "Tv Lcd Politron", 700.000, "http://placehold.it/200x100"));
			productService.save(new Product(4L, "Kamera DSLR Nikon", 800.000, "http://placehold.it/200x100"));
			productService.save(new Product(5L, "Kamera DSRL Cannon", 770.000, "http://placehold.it/200x100"));
			productService.save(new Product(6L, "Samsung Galaxy S8", 790.000, "http://placehold.it/200x100"));
			productService.save(new Product(7L, "Kamera Gopro", 900.000, "http://placehold.it/200x100"));

		};
	}
	**/
}
