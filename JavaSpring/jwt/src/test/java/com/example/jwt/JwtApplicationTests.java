package com.example.jwt;

import com.example.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {
	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}
	@Test
	void tokenCreate(){
		var claims = new HashMap<String,Object>();
		claims.put("user_id",923);

		var expireAt = LocalDateTime.now().plusMinutes(10);

		var jwtToken = jwtService.create(claims, expireAt);

		System.out.println(jwtToken);
	}
	@Test
	void tokenValidation(){
		var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjMsImV4cCI6MTY5MzM4NDc2NX0.BGvwgf7iGhO3kkNj087CHeso0DNShjMNcwrwbozDUKs";
		jwtService.validation(token);
	}

}
