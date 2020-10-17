package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/**")
@RestController
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @CrossOrigin
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
    // вывод продуктов
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }


    @PostMapping( path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product addProduct(@RequestBody Product product) {
        product.setId(0L);
        product = productService.saveOrUpdate(product);
        return product;
    }

    @PutMapping(path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product) {
        product = productService.saveOrUpdate(product);
        return product;
    }

    @DeleteMapping("/products/{productId}")
    public int deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return HttpStatus.OK.value();
    }


    @GetMapping("")
    public String shopPage(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop-page";
    }

    @GetMapping("/ajax")
    public String ajaxShopPage(Model model) {
       // List<Product> allProducts = productService.getAllProducts();
       // model.addAttribute("products", allProducts);
        return "shop-page-ajax";
    }
}
