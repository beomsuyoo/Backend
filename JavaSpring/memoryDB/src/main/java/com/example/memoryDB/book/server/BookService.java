package com.example.memoryDB.book.server;

import com.example.memoryDB.book.db.BookRepository;
import com.example.memoryDB.book.model.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookEntity create(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }
}
