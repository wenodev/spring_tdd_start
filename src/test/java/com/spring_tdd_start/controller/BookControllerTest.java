package com.spring_tdd_start.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_get() throws Exception {
        mockMvc.perform(get("/books" + "/" + 1L))
                .andExpect(status().isOk());
    }

}