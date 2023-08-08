package com.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import model.UserRequest;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // log
@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put") // create or update
    public void put(
            @RequestBody
            UserRequest userRequest
    ){
        log.info("request: {}", userRequest); // userRequest object maps with {} using toString
    }
}
