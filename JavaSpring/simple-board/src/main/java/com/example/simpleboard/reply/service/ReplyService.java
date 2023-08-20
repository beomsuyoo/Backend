package com.example.simpleboard.reply.service;

import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.service.PostConverter;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    public ReplyEntity create(
            ReplyRequest replyRequest
    ){
        var postEntity = postRepository.findById(replyRequest.getPostId());
        if(postEntity.isEmpty()){
            throw new RuntimeException("Post does not exist : "+replyRequest.getPostId());
        }

        var entity = ReplyEntity.builder()
                .post(postEntity.get())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .status("REGISTERED")
                .repliedAt(LocalDateTime.now())
                .build();
        return replyRepository.save(entity);
    }
    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }
}
