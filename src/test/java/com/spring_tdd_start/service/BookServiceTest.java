package com.spring_tdd_start.service;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.errors.BookNotFoundException;
import com.spring_tdd_start.repository.BookRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BookServiceTest {

    private BookRepository bookRepository = mock(BookRepository.class);
    private BookService bookService = new BookService(bookRepository);

    @Test
    void detail메소드는_id가존재한다면_200을리턴한다() {
        given(bookRepository.findById(1L)).willReturn(Optional.of(new Book(1L, "테스트주도개발", "켄트벡")));
        Book book = bookService.detail(1L);
        assertThat(book.getName()).isEqualTo("테스트주도개발");
    }

    @Test
    void detail메소드는_id가존재하지않는다면_404를리턴한다(){
        assertThatThrownBy(() -> bookService.detail(1000L))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("no book id: " + 1000L);
    }

}