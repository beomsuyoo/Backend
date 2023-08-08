package com.example.restapi.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import model.BookRequest;
import model.UserRequest;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostApiController { // send to server
    @PostMapping("/post")
    public BookRequest post(
            @RequestBody BookRequest bookRequest
            ){
        System.out.println(bookRequest);
        return bookRequest;
    }
    @PostMapping("/user")
    public void User(
            @RequestBody UserRequest userRequest
            ){
        System.out.println(userRequest);

    }
}
