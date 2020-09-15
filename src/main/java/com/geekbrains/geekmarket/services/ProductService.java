package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.repositories.ProductRepository;
import com.geekbrains.geekmarket.utils.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        Page<Product> pr = productRepository.findAll(PageRequest.of(0,10));
        //System.out.println(pr);
        return pr.stream().collect(Collectors.toList());
    }

    public List<Product> getProductsByVendorCode(String code) {
        return productRepository.findAllByVendorCode(code);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }



    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id = " + id + " not found");
        }
        productRepository.delete(product.get());
    }

    public List<Product> getAllProductsList() {
        return (List<Product>) productRepository.findAll();
    }


}
