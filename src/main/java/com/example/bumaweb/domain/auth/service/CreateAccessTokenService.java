package com.example.bumaweb.domain.auth.service;

import com.example.bumaweb.domain.auth.domain.RefreshToken;
import com.example.bumaweb.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bumaweb.domain.auth.exception.RefreshTokenException;
import com.example.bumaweb.domain.auth.presentation.dto.res.AccessTokenResponse;
import com.example.bumaweb.domain.user.domain.User;
import com.example.bumaweb.domain.user.domain.repository.UserRepository;
import com.example.bumaweb.global.security.jwt.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccessTokenService {
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;

  @Transactional
  public AccessTokenResponse execute(String token) { // 액세스 토큰 재발급
    RefreshToken refreshToken = getRefreshToken(token);
    return new AccessTokenResponse(jwtTokenProvider
            .createAccessToken(refreshToken.getEmail()));
  }
 private RefreshToken getRefreshToken(String token) {
   return refreshTokenRepository.findById(token)
           .orElseThrow(() -> new RefreshTokenException("refresh token is not found"));
 }
}
