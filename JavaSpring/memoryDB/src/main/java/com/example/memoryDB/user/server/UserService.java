package com.example.memoryDB.user.server;

import com.example.memoryDB.user.db.UserRepository;
import com.example.memoryDB.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService { // service logic and db
    @Autowired
    private UserRepository userRepository; // call repository
    public UserEntity save(UserEntity user){ // get UserEntity as a parameter and save it to db connected to repository
        return userRepository.save(user);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public void delete(long id){
        userRepository.delete(id);
    }

    public List<UserEntity> filterScore(int score){
        return userRepository.findAllScoreGreaterThan(score);
    }
}
