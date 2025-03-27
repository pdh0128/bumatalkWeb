package com.example.bumaweb.domain.auth.presentation.dto.presentation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponse {
  private final String acccessToken;
  private final String refreshToken;
}
