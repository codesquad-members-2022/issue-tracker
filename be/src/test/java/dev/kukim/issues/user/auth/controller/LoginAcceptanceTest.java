package dev.kukim.issues.user.auth.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import dev.kukim.issues.MysqlTestContainer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("Login 인수테스트")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LoginAcceptanceTest extends MysqlTestContainer {

	@LocalServerPort
	int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	@DisplayName("로그인되지 않는 유저가 인가되지 않은 API 접속한다면, 에러가 발생한다.")
	void 인가되지않은_api_접근한다면_접근권한_에러를_반환한다() {
		given()
			.accept(MediaType.APPLICATION_JSON_VALUE)

		.when()
			.get("/api/issues")

		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("로그인이 필요합니다."));
	}


	@Test
	@DisplayName("유효하지않은 토큰으로 API 접속한다면, 에러가 발생한다.")
	void 유효하지않은_계정으로_접속한다면_에러를발생한다() {
		given()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + "잘못된.jwt.key")

		.when()
			.get("/api/issues")

		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("유효하지 않은 토큰입니다."));
	}

}
