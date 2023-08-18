package com.example.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "")
    public void hello() {
        var list = List.of("a");
        var element = list.get(1);
        throw new RuntimeException("run time exception");
    }
}
