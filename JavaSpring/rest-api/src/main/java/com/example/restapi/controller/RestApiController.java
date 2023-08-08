package com.example.restapi.controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController // assign RestController to RestApiController class
@RequestMapping("/api") // the controller receives address "/api"
public class RestApiController { // get from server
    @GetMapping(path="/hello") // map with a method for address /api/hello
    public String hello(){
        var html = "<html> <body> <h1> Hello Spring Boot </h1> </body></html>";
        return html;
    }
    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")  // map with a method for address /echo/{message}
    public String echo(
            // variables in the url
        @PathVariable String message,
        @PathVariable int age,
        @PathVariable boolean isMan
    ){
        System.out.println(message);
        System.out.println(age);
        System.out.println(isMan);

        return message.toUpperCase();
    }

    @GetMapping(path = "/book")
    public void queryParam( // starts with ? variable=input& form
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam(name = "issued-month") String issuedMonth,
            @RequestParam String issued_day
            ){
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issued_day);
    }
}
