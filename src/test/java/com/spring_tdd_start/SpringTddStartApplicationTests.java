package com.spring_tdd_start;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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
		System.out.println("port: " + port);
		RestAssured.port = port;
	}

	@Test
	void test1(){
		System.out.println("test1");
	}
}
