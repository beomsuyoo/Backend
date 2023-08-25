package com.example.filter.controller;

import com.example.filter.interceptor.OpenApi;
import com.example.filter.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @OpenApi
    @PostMapping("")
    public UserRequest register(
            @RequestBody UserRequest userRequest // an already objectified json
            //HttpEntity http is casted as well
    ){
        log.info("{}",userRequest);
        //throw new NumberFormatException("");
        return userRequest;
    }

    @GetMapping("/hello")
    public void hello(){
        log.info("hello");
    }
}
