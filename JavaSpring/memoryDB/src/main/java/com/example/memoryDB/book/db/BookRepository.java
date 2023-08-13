package com.example.memoryDB.book.db;

import com.example.memoryDB.book.model.BookEntity;
import com.example.memoryDB.db.SimpleDataRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRepository extends SimpleDataRepository<BookEntity,Long> {

}
