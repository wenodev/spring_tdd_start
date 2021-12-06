package com.spring_tdd_start.service;

import com.spring_tdd_start.domain.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public Book detail(Long id){
        return new Book(id, "테스트주도개발", "켄트벡");
    }
}
