package com.geekbrains.eureka.client;

import com.geekbrains.geekmarket.entities.Product;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ProductController {

    @RequestMapping("/getproducts")
    List<Product> getProducts();

}
