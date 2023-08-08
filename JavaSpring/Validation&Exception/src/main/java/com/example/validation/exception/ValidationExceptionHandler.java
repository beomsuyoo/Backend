package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Api> validationException( // use ResponseEntity to manipulate status and data type is object in this case
            MethodArgumentNotValidException exception
    ){
        log.error("",exception);

        var errorMessageLists = exception.getFieldErrors().stream() // exceptions contains erros occurs so use it to stream
                .map(it-> { // map it with format
                    var format = "%s: {%s}은 %s";
                    var message = String.format(format,it.getField(),it.getRejectedValue(),it.getDefaultMessage());
                    return message;
                }).collect(Collectors.toList()); //collect the mapped strings to a list

        var error = Api.Error // build Error class
                .builder()
                .errorMessage(errorMessageLists)
                .build();
        var errorResponse = Api // build Api class using the Error Class
                .builder()
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .resultMessage(HttpStatus.BAD_REQUEST.name())
                .error(error) // data is null
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); // wrap the api into ResponseEntity with manipulated status
    }
}
