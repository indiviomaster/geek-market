package com.geekbrains.eureka.client;

import com.geekbrains.geekmarket.entities.Product;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ProductController {

    @RequestMapping("/products")
    List<Product> getProducts();

}
