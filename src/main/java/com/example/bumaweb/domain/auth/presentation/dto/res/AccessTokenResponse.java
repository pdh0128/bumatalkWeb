package com.example.bumaweb.domain.auth.presentation.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenResponse {
  private final String accessToken;
}
