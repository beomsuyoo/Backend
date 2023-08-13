package com.example.memoryDB.book.controller;

import com.example.memoryDB.book.model.BookEntity;
import com.example.memoryDB.book.server.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @PutMapping("")
    public BookEntity create(
            @RequestBody BookEntity bookEntity
    ){
        return bookService.create(bookEntity);
    }
    @GetMapping("/all")
    public List<BookEntity> findAll(){
        return bookService.findAll();
    }
}
