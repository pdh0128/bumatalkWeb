package com.example.bumaweb.global.exception;

import com.example.bumaweb.domain.board.domain.Board;
import com.example.bumaweb.domain.board.exception.BoardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BoardNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleBoardNotFoundException(BoardNotFoundException e) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
