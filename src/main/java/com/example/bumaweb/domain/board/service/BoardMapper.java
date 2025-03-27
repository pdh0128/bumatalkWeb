package com.example.bumaweb.domain.board.service;

import com.example.bumaweb.domain.board.domain.Board;
import com.example.bumaweb.domain.board.presentation.dto.req.BoardRequest;
import com.example.bumaweb.domain.board.presentation.dto.res.BoardResponse;
import org.springframework.stereotype.Service;

@Service
public class BoardMapper {
  public Board toBoard(BoardRequest request) {
    return Board.builder()
            // .username()
            .description(request.description())
            .text(request.text())
            .build();
  }

  public BoardResponse fromBoardToBoardResponse(Board board) {
    return new BoardResponse(
            board.getBoardId(),
            board.getUsername(),
            board.getDescription(),
            board.getText(),
            board.getLikes()
    );
  }
}
