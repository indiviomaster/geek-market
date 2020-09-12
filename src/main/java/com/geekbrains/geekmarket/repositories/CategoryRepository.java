package com.geekbrains.geekmarket.repositories;

import com.geekbrains.geekmarket.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findById(Long id);

}
