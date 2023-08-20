package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString // infinite loop when logging
@Entity(name="post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* post and board has N:1 relationship
    @ManyToOne is considered column and puts "_id" at the end of variable name */
    @ManyToOne
//    @JsonIgnore // Get rid of circular reference or make DTO
//    @ToString.Exclude
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(
            mappedBy = "post"
    )
    private List<ReplyEntity> replyEntityList = List.of();

}
