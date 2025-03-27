package com.example.bumaweb.domain.user.presentation.dto.req;

public record UserCreateRequest(
        String username,
        String email,
        String password
) {
}
