package com.spring_tdd_start.controller;

import com.spring_tdd_start.domain.Book;
import com.spring_tdd_start.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void test_detail() throws Exception {

        given(bookService.detail(1L)).willReturn(new Book(1L, "테스트주도개발", "켄트벡"));

        mockMvc.perform(get("/books" + "/" + 1L))
                .andExpect(content().string(containsString("켄트벡")))
                .andExpect(status().isOk());
    }

}