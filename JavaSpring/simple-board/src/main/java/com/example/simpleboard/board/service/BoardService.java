package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;
    public BoardDto view(Long id) {

        var entity = boardRepository.findById(id).get();
        return boardConverter.toDto(entity);
    }

    public BoardDto create(
            BoardRequest boardRequest
    ){
        var request = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var saveEntity = boardRepository.save(request);
        return boardConverter.toDto(saveEntity);
    }
}
