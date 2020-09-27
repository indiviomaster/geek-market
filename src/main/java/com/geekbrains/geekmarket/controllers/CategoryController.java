package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("")
    public String categoriesPage(Model model) {
        List<Category> allCats = categoryService.getAllCategories();
        model.addAttribute("categories", allCats);
        return "categories";
    }

    @GetMapping("/addcategory")
    public String addCategoryForm(Model model) {

        model.addAttribute("category", new Category());
        return "category";
    }



}
