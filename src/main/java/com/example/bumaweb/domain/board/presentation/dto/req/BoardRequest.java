package com.example.bumaweb.domain.board.presentation.dto.req;


import jakarta.validation.constraints.NotNull;

public record BoardRequest (
        @NotNull(message="Board username is required")
        String username,
        String description,
        @NotNull(message="Board text is required")
        String text
) {

}
