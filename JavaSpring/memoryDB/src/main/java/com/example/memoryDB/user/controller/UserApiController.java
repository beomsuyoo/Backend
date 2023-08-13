package com.example.memoryDB.user.controller;

import com.example.memoryDB.user.model.UserEntity;
import com.example.memoryDB.user.server.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController { // handle requests from clients

    private final UserService userService; // call service

    @PutMapping("")
    public UserEntity create(
            @RequestBody UserEntity userEntity
    ){
        return userService.save(userEntity); //save the request to the service
    }

    @GetMapping("/all")
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @DeleteMapping ("/del/{DeleteId}")
    public void delete(
            @PathVariable Long DeleteId
    ){
        userService.delete(DeleteId);
    }

    @GetMapping("/score")
    public List<UserEntity> filterScore(
            @RequestParam int score
    ) {
        return userService.filterScore(score);
    }




}
