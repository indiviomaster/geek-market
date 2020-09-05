package com.geekbrains.geekmarket.repositories;

import com.geekbrains.geekmarket.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository <Book, Integer>{
    List<Book> findAll();
    Optional<Book> findById(int id);
}
