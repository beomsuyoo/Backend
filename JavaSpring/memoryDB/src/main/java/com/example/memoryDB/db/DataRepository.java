package com.example.memoryDB.db;

import java.util.Optional;
import java.util.List;

public interface DataRepository<T,ID> extends Repository<T,ID>{
    //create update
    T save(T data);

    //read
    Optional<T> findByID(ID id);

    List<T> findAll();

    //delete
    void delete(ID id);

}
