package com.example.bumaweb.domain.board.service;

import com.example.bumaweb.domain.board.domain.Board;
import com.example.bumaweb.domain.board.domain.repository.BoardRepository;
import com.example.bumaweb.domain.board.exception.BoardNotFoundException;
import com.example.bumaweb.domain.board.presentation.dto.req.BoardRequest;
import com.example.bumaweb.domain.board.presentation.dto.req.BoardUpdateRequest;
import com.example.bumaweb.domain.board.presentation.dto.res.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardMapper mapper;
  private final BoardRepository repository;
  public void createBoard(BoardRequest request) {
    var board = mapper.toBoard(request);
    repository.save(board);
  }

  public BoardResponse findBoardByBoardId(Long boardId) {
    return repository.findById(boardId)
            .map(mapper::fromBoardToBoardResponse)
            .orElseThrow(() -> new BoardNotFoundException("No Board found with provided BoardId"));
  }

  public List<BoardResponse> findAll() {
    return repository.findAll().stream()
            .map(mapper::fromBoardToBoardResponse)
            .collect(Collectors.toList());
  }

  public void deleteBoardByBoardId(Long boardId) {
    repository.deleteById(boardId);
  }

  public void updateBoardByBoardId(BoardUpdateRequest request) {
    Board board = repository.findById(request.boardId()).orElseThrow(() -> new BoardNotFoundException("No Board found with provided BoardId"));
    board.setDescription(request.description());
    board.setText(request.text());
  }

  public void updateBoardLikeByBoardId(Long boardId) {
    Board board = repository.findById(boardId).orElseThrow(() -> new BoardNotFoundException("No Board found with provided BoardId"));
    board.setLikes(board.getLikes() + 1);
  }
}
