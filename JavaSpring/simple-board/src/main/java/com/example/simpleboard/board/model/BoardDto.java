package com.example.simpleboard.board.model;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {
    private Long id;

    private String boardName;

    private String status;

    private List<PostDto> postDtoList = List.of();
}
