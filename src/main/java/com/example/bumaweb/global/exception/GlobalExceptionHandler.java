package com.example.bumaweb.global.exception;

import com.example.bumaweb.domain.auth.exception.RefreshTokenException;
import com.example.bumaweb.domain.board.domain.Board;
import com.example.bumaweb.domain.board.exception.BoardNotFoundException;
import com.example.bumaweb.global.security.login.exception.UnSuccessfulAuthenticationException;
import org.apache.coyote.Response;
import org.springframework.data.redis.RedisSystemException;
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
  @ExceptionHandler(UnSuccessfulAuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleUnSuccessfulAuthenticationException(UnSuccessfulAuthenticationException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  @ExceptionHandler(RefreshTokenException.class)
  public ResponseEntity<ErrorResponse> handleRefreshTokenException(RefreshTokenException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
  @ExceptionHandler(RedisSystemException.class)
  public ResponseEntity<ErrorResponse> handleRedisSystemException(RedisSystemException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}

