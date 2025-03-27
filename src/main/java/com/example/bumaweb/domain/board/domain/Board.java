package com.example.bumaweb.domain.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;
  private String username;
  private String description;
  private String text;
  private Long likes;
  public Board(Long boardId, String username, String description, String text, Long likes) {
    this.boardId = boardId;
    this.username = username;
    this.description = description;
    this.text = text;
    this.likes = 0L;
  }
}
