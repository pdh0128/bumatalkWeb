package com.example.bumaweb.domain.board.presentation.dto.req;


import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest (
        @NotNull(message="Board Updating is required BoardId")
        Long boardId,
        String description,
        String text
) {
}
