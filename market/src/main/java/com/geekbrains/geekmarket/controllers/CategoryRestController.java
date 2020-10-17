package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/**")
@RestController
public class CategoryRestController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @CrossOrigin
    @GetMapping("/categories/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }


    @PostMapping(path = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Category addCategory(@RequestBody Category category) {
        category.setId(0L);
        category = categoryService.saveOrUpdate(category);
        return category;
    }

    @PutMapping(path = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Category updateCategory(@RequestBody Category category) {
        category = categoryService.saveOrUpdate(category);
        return category;
    }

    @DeleteMapping("/categories/{categoryId}")
    public int deleteProduct(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return HttpStatus.OK.value();
    }

}
