package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.ViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostEntity save(
            PostRequest postRequest
    ){
        var request = PostEntity.builder()
                .boardId(1L)//대기
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .content(postRequest.getContent())
                .title(postRequest.getTitle())
                .postedAt(LocalDateTime.now())
                .status("REGISTERED")
                .build();
        return postRepository.save(request);
    }

    public PostEntity view(
            ViewRequest viewRequest
    ){
        return postRepository.findFirstByIdAndStatusOrderByIdDesc(viewRequest.getPostId(),"REGISTERED") // findById returns Optional
                .map(it -> { // if data exist
                    if(!it.getPassword().equals(viewRequest.getPassword())){
                        String format = "Password does not match %s vs %s";
                        throw new RuntimeException(String.format(format,viewRequest.getPassword(),it.getPassword()));
                    }
                    return it;
                }).orElseThrow( // if null throw
                        ()-> new RuntimeException("The post does not exist : " + viewRequest.getPostId())
                );
    }

    public List<PostEntity> viewAll(){
        return postRepository.findAll();
    }

    public PostEntity delete(
            ViewRequest viewRequest
    ){
        return postRepository.findById(viewRequest.getPostId()) // findById returns Optional
                .map(it -> { // if data exist
                    if(!it.getPassword().equals(viewRequest.getPassword())){
                        String format = "Password does not match %s vs %s";
                        throw new RuntimeException(String.format(format,viewRequest.getPassword(),it.getPassword()));
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;
                }).orElseThrow( // if null throw
                        ()-> new RuntimeException("The post does not exist " + viewRequest.getPostId())
                );

    }
}
