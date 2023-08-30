package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import com.example.cookie.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final UserRepository userRepository;
    @GetMapping("/me")
    public UserDto me( // get information about the logged in user
                       HttpServletRequest httpServletRequest,
                       @CookieValue(name ="authorization-cookie", required = false) String authorizationCookie
    ){
        log.info("authorizationCookie: {}",authorizationCookie);

        var optionalUserDto = userRepository.findById(authorizationCookie);
        return optionalUserDto.get();
        /*var cookies = httpServletRequest.getCookies();
        if(cookies!=null){ // in case cookie is outdated
            for(Cookie cookie:cookies){
                log.info("key {}, value {}", cookie.getName(),cookie.getValue());
            }
        }*/
    }
}
