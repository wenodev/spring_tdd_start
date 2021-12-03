package com.spring_tdd_start.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_get(){

    }

}