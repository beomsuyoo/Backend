package com.example.simpleboard.post.controller;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.ViewRequest;
import com.example.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;
    @PostMapping("")
    public PostEntity save(
            @Valid
            @RequestBody PostRequest postRequest
    ){
        return postService.save(postRequest);
    }

    @PostMapping("/view") // why post? need pw
    public PostEntity view(
            @Valid
            @RequestBody ViewRequest viewRequest
    ){
        return postService.view(viewRequest);
    }

    @GetMapping("/all")
    public Api<List<PostEntity>> viewAll(
            @PageableDefault(page = 0, size = 10)
            Pageable pageable
    ){
        return postService.viewAll(pageable);
    }

    @PostMapping("/delete") // why post? need pw
    public PostEntity delete(
            @Valid
            @RequestBody ViewRequest viewRequest
    ){
        return postService.delete(viewRequest);
    }
}
