package com.example.session.controller;

import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @GetMapping("/me")
    public UserDto me( // get information about the logged in user
            HttpSession httpSession
    ){
        var userObject = httpSession.getAttribute("USER");
        if(userObject!=null){ // in case session is outdated
            var userDto = (UserDto) userObject;
            return userDto;
        }
        return null;
    }
}
