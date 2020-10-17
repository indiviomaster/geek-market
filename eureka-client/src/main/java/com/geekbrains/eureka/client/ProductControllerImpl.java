package com.geekbrains.eureka.client;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.CategoryService;
import com.geekbrains.geekmarket.services.ProductService;


@RestController
public class ProductControllerImpl implements ProductController {
    @Autowired
    @Lazy
    private final EurekaClient eurekaClient;

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

}
