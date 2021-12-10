package com.spring_tdd_start.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.errors.BookNotFoundException;
import com.spring_tdd_start.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void detail메소드는_id가존재한다면_200을리턴한다() throws Exception {
        given(bookService.detail(eq(1L))).willReturn(new Book(1L, "테스트주도개발", "켄트벡"));

        mockMvc.perform(get("/books/1"))
                .andExpect(content().string(containsString("켄트벡")))
                .andExpect(status().isOk());
    }

    @Test
    void detail메소드는_id가존재하지않는다면_404를리턴한다() throws Exception {
        given(bookService.detail(1000L)).willThrow(new BookNotFoundException("no book id: "+ 1000L));

        mockMvc.perform(get("/books/1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void list메소드는_200을리턴한다() throws Exception {
        given(bookService.list()).willReturn(List.of(new Book(1L, "테스트주도개발", "켄트벡")));

        mockMvc.perform(get("/books"))
                .andExpect(content().string(containsString("켄트벡")))
                .andExpect(status().isOk());
    }

    @Test
    void create메소드는_상품등록이된다면_등록된상품과_201을리턴한다() throws Exception {
        // arrange
        given(bookService.create(any(Book.class))).willReturn(new Book(1L, "테스트주도개발", "켄트벡"));

        // act
        ResultActions resultActions = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Book(1L, "테스트주도개발", "켄트벡"))));

        // assert
        resultActions.andExpect(status().isCreated())
                .andExpect(content().string(containsString("테스트주도개발")));
    }

    @Test
    void update_메소드는_id값이_존재한다면_상품을_수정하고_상품정보와_상태값_200을_리턴한다() throws Exception {
        // arrange
        given(bookService.update(eq(1L), any(Book.class))).willReturn(new Book("TDD", "Kent Beck"));

        // act
        ResultActions resultActions = mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Book("TDD", "Kent Beck"))));

        // assert
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Kent Beck")));
    }

    @Test
    void update_메소드는_id값이_없다면_상태값_404를_리턴한다()  throws Exception {
        // arrange
        given(bookService.update(eq(1000L), any(Book.class))).willThrow(new BookNotFoundException("no book id : 1000"));

        // act
        ResultActions resultActions = mockMvc.perform(put("/books/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Book("TDD", "Kent Beck"))));

        // assert
        resultActions
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_메소드는_id가_존재한다면_book을지우고_200을_리턴한다() throws Exception {
        // arrange
        given(bookService.delete(1L)).willReturn(new Book(1L, "테스트주도개발", "켄트벡"));

        // act
        ResultActions perform = mockMvc.perform(delete("/books/1"));

        // assert
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L));

    }

}