package com.spring_tdd_start.service;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.repository.BookRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BookServiceTest {

    private BookRepository bookRepository = mock(BookRepository.class);
    private BookService bookService = new BookService(bookRepository);

    @Test
    void test_detail() {
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(new Book(1L, "테스트주도개발", "켄트벡")));
        Book book = bookService.detail(1L);
        assertThat(book.getName()).isEqualTo("테스트주도개발");
    }
}