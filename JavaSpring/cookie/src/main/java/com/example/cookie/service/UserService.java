package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();
            if(userDto.getPassword().equals(pw)){
                // cookie setting
                var cookie = new Cookie("authorization-cookie",userDto.getId());
                cookie.setDomain("localhost"); // xxx.com
                cookie.setPath("/"); // all root
                cookie.setMaxAge(-1); // -1 session or second
                cookie.setHttpOnly(true); // cannot see cookie in javascript
               /* cookie.setSecure(true); // only https*/
                httpServletResponse.addCookie(cookie);
            } else {
                throw new RuntimeException("Password Not Match");
            }
        } else {
            throw new RuntimeException("User Not Found");
        }
    }
}
