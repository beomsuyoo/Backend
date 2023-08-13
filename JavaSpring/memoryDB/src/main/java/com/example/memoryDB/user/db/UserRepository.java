package com.example.memoryDB.user.db;

import com.example.memoryDB.db.SimpleDataRepository;
import com.example.memoryDB.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // use this annotation to make internal class Bean
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {
    public List<UserEntity> findAllScoreGreaterThan(int score){
        return this.findAll()
                .stream()
                .filter(it-> it.getScore() >= score)
                .collect(Collectors.toList());
    }
}
