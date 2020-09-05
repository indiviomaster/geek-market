package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Book;
import com.geekbrains.geekmarket.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String booksPage(Model model) {
        List<Book> allBooks = bookService.getAllBooks();
        model.addAttribute("books", allBooks);
        return "books";
    }

    @GetMapping("/newbook")
    public String newBookForm(Model model) {

        model.addAttribute("book", new Book());
        return "book";
    }

}
