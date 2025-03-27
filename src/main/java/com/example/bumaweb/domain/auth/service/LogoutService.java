package com.example.bumaweb.domain.auth.service;

import com.example.bumaweb.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bumaweb.domain.auth.presentation.dto.req.RefreshTokenRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
  private final RefreshTokenRepository refreshTokenRepository;

  @Transactional
  public void execute(RefreshTokenRequest request) {
    refreshTokenRepository.deleteRefreshTokenByToken(request.getRefreshToken());
  }

}
