package com.spring_tdd_start.controller;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{id}")
    public Book detail(@PathVariable Long id) {
        return bookService.detail(id);
    }

}
