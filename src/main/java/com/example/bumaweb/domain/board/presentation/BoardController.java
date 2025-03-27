package com.example.bumaweb.domain.board.presentation;

import com.example.bumaweb.domain.board.exception.BoardNotFoundException;
import com.example.bumaweb.domain.board.presentation.dto.req.BoardRequest;
import com.example.bumaweb.domain.board.presentation.dto.req.BoardUpdateRequest;
import com.example.bumaweb.domain.board.presentation.dto.res.BoardResponse;
import com.example.bumaweb.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/board")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService service;
  @PostMapping
  public ResponseEntity<Void> createBoard(@RequestBody  @Valid BoardRequest request) {
    service.createBoard(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  @GetMapping("/{boardId}")
  public ResponseEntity<BoardResponse> findBoardByBoardId(@PathVariable("boardId") Long boardId) {
      return ResponseEntity.ok(service.findBoardByBoardId(boardId));
  }
  @GetMapping
  public ResponseEntity<List<BoardResponse>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }
  @PatchMapping("/{boardId}/like")
  public ResponseEntity<Void> updateBoardLikeByBoardId(@PathVariable("boardId") Long boardId) {
    service.updateBoardLikeByBoardId(boardId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  @DeleteMapping("/{boardId}")
  public ResponseEntity<Void> deleteBoardByBoardId(@PathVariable("boardId") Long boardId) {
    service.deleteBoardByBoardId(boardId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  @PatchMapping("/{board-id}")
  public ResponseEntity<Void> updateBoardByBoardId(@RequestBody @Valid BoardUpdateRequest request) {
    service.updateBoardByBoardId(request);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
