package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Book;
import com.geekbrains.geekmarket.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {

        Page<Book> pr = bookRepository.findAll(PageRequest.of(0,4));
        return pr.stream().collect(Collectors.toList());
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        return null;
    }
}
