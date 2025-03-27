package com.example.bumaweb.domain.auth.presentation;

import com.example.bumaweb.domain.auth.domain.RefreshToken;
import com.example.bumaweb.domain.auth.presentation.dto.req.AccessTokenRequest;
import com.example.bumaweb.domain.auth.presentation.dto.req.RefreshTokenRequest;
import com.example.bumaweb.domain.auth.presentation.dto.res.AccessTokenResponse;
import com.example.bumaweb.domain.auth.service.CreateAccessTokenService;
import com.example.bumaweb.domain.auth.service.LogoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final LogoutService logoutService;
  private final CreateAccessTokenService createAccessTokenService;

//  @PostMapping 체크용
//  public ResponseEntity<Void> checkJWT (@RequestBody @Valid RefreshTokenRequest request) {
//    log.warn(request.getRefreshToken());
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//  }

  @PostMapping("/refresh")
  public ResponseEntity<AccessTokenResponse> createNewAccessToken(@RequestBody @Valid RefreshTokenRequest request) {
    return ResponseEntity.ok(createAccessTokenService.execute(request.getRefreshToken()));
  }

  @PostMapping("/logout")
  public void logout(@RequestBody @Valid RefreshTokenRequest request) {
    logoutService.execute(request);
  }
}
