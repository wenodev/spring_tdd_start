package com.spring_tdd_start.controller;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/books")
    public List<Book> list() {
        return bookService.list();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookService.create(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        return bookService.update(id, book);
    }

    @DeleteMapping("/books/{id}")
    public Book delete(@PathVariable Long id){
        return bookService.delete(id);
    }

}
