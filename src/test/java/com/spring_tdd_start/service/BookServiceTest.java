package com.spring_tdd_start.service;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.errors.BookNotFoundException;
import com.spring_tdd_start.repository.BookRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BookServiceTest {

    private BookRepository bookRepository = mock(BookRepository.class);
    private BookService bookService = new BookService(bookRepository);

    @Test
    void detail메소드는_id가존재한다면_book을리턴한다() {
        // arrange
        given(bookRepository.findById(1L)).willReturn(Optional.of(new Book(1L, "테스트주도개발", "켄트벡")));

        // act
        Book book = bookService.detail(1L);

        //assert
        assertThat(book.getName()).isEqualTo("테스트주도개발");
    }

    @Test
    void detail메소드는_id가존재하지않는다면_BookNotFoundException를던진다(){
        assertThatThrownBy(() ->
                // act
                bookService.detail(1000L))

                // assert
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("no book id: " + 1000L);
    }

    @Test
    void list메소드는_bookList가존재한다면_bookList를리턴한다(){
        // arrange
        given(bookRepository.findAll()).willReturn(List.of(new Book(1L, "테스트주도개발", "켄트벡")));

        // act
        List<Book> books = bookService.list();

        //assert
        assertThat(books.size()).isEqualTo(1);
        assertThat(books.get(0).getName()).isEqualTo("테스트주도개발");
    }

    @Test
    void list메소드는_booklist가_없다면_비어있는_리스트를_리턴한다(){
        // arrange
        given(bookRepository.findAll()).willReturn(List.of());

        // act
        List<Book> books = bookService.list();

        //assert
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void create메소드는_book이생성된다면_생성된book을리턴한다(){
        // arrange
        given(bookRepository.save(any(Book.class))).willReturn(new Book(1L, "테스트주도개발", "켄트벡"));

        // act
        Book book = bookService.create(new Book(1L, "테스트주도개발", "켄트벡"));

        // assert
        assertThat(book.getName()).isEqualTo("테스트주도개발");
    }

}