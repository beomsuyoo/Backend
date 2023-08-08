package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import com.example.validation.exception.ValidationExceptionHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @PostMapping("")
    public Api<UserRegisterRequest> register( // Use inheritance to return both Object and UserRegisterRequest
            @Valid // throws MethodArgumentNotValidException if it detects error which will be handled at the exception handler
            @RequestBody Api<UserRegisterRequest> userRegisterRequest // requested object
    ){
        log.info("init ", userRegisterRequest);
        var body = userRegisterRequest.getData(); // echoing so getData from the request
        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.name())
                .data(body)
                .build();

        return response; // Api<UserRegisterRequest> type
    }
}
