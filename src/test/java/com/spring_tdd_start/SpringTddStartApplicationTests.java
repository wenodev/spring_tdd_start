package com.spring_tdd_start;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.awt.*;
import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/*
# 상황
- 온라인 서점을 오픈하는 회사

# 요청사항
관리자: 책을 등록하고 조회할 수 있게 해주세요
개발자: 네(post, get 필요). 책을 등록할 때 어떤 정보가 필요한가요?
관리자: 책의 이름, 저자, 가격이 필요합니다.
개발자: 네, 조회할 때도 책의 이름, 저자, 가격을 보여주면 되나요?
관리자: 네, 맞습니다.

 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringTddStartApplicationTests {

	@LocalServerPort
	public int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void test_getBook(){
		given()
				.accept(MediaType.APPLICATION_JSON.toString())
		.when()
				.get("/books/1")
		.then()
				.log().all()
				.statusCode(HttpStatus.OK.value())
				.assertThat().body("name", equalTo("테스트주도개발"));
	}
}
