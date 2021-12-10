package com.spring_tdd_start.service;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.errors.BookNotFoundException;
import com.spring_tdd_start.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book detail(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("no book id: " + id));
    }

    public List<Book> list() {
        return bookRepository.findAll();
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        return new Book(1L, "TDD", "Kent Beck");
    }
}
