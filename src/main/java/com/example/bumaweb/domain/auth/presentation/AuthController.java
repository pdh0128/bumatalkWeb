package com.example.bumaweb.domain.auth.presentation;

import com.example.bumaweb.domain.auth.domain.RefreshToken;
import com.example.bumaweb.domain.auth.presentation.dto.req.AccessTokenRequest;
import com.example.bumaweb.domain.auth.presentation.dto.req.RefreshTokenRequest;
import com.example.bumaweb.domain.auth.presentation.dto.res.AccessTokenResponse;
import com.example.bumaweb.domain.auth.service.CreateAccessTokenService;
import com.example.bumaweb.domain.auth.service.LogoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final LogoutService logoutService;
  private final CreateAccessTokenService createAccessTokenService;

  @PostMapping("/refresh")
  public ResponseEntity<AccessTokenResponse> createNewAccessToken(@RequestBody @Valid RefreshTokenRequest request) {
    return ResponseEntity.ok(createAccessTokenService.execute(request.getRefreshToken()));
  }

  @PostMapping("/logout")
  public void logout(@RequestBody @Valid RefreshTokenRequest request) {
    logoutService.execute(request);
  }
}
