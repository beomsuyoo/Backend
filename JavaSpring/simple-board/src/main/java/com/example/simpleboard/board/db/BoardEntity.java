package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany( // board and post has 1:N relationship
            mappedBy = "board"
    )
    @Where(clause = "status = 'REGISTERED'") // show registered only
    @OrderBy(clause = "id desc") // order by descending order
    private List<PostEntity> postEntityList = List.of();

}
