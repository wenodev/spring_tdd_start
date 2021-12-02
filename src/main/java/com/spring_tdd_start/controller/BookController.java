package com.spring_tdd_start.controller;

import com.spring_tdd_start.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return new Book(id, "tdd", "kentbeck");
    }

}
