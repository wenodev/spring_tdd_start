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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void detail메소드는_id가존재한다면_200을리턴한다() throws Exception {
        given(bookService.detail(1L)).willReturn(new Book(1L, "테스트주도개발", "켄트벡"));

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
        given(bookService.list()).willReturn(List.of(new Book(1L, "테스트주도개발", "켄트벡")));

        // act
        ResultActions perform = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Book(1L, "테스트주도개발", "켄트벡"))));

        // assert
        perform.andExpect(status().isCreated());
    }

}