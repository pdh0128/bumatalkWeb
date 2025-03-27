package com.example.bumaweb.domain.board.presentation.dto.res;

public record BoardResponse (
        Long boardId,
        String username,
        String description,
        String text,
        Long likes
) {
}
