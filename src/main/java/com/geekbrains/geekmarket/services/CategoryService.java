package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.repositories.CategoryRepository;
import com.geekbrains.geekmarket.utils.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        Page<Category> cr = categoryRepository.findAll(PageRequest.of(0,10));
        System.out.println(cr);
        return cr.stream().collect(Collectors.toList());
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }
        return null;
    }

    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException("Category with id = " + id + " not found");
        }
        categoryRepository.delete(category.get());
    }

}
