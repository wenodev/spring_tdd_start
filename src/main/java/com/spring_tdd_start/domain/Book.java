package com.spring_tdd_start.domain;

public class Book {
    private Long id;
    private String name;
    private String author;

    public Book(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
