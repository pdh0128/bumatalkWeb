package com.example.bumaweb.global.security.jwt;

import com.example.bumaweb.domain.auth.domain.RefreshToken;
import com.example.bumaweb.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bumaweb.domain.auth.exception.RefreshTokenException;
import com.example.bumaweb.global.config.properties.JwtProperties;
import com.example.bumaweb.global.security.principle.AuthDetails;
import com.example.bumaweb.global.security.principle.AuthDetailsService;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {
  private final JwtProperties jwtProperties;
  private final AuthDetailsService authDetailsService;

  private final static String ACCESS_TOKEN = "access_token";
  private final static String REFRESH_TOKEN = "refresh_token";
  private final RefreshTokenRepository refreshTokenRepository;

  public String createAccessToken(String email) {
    return createToken(email, ACCESS_TOKEN, jwtProperties.getAcessTime());
  }

  private String createToken(String email, String type, Long time) {
    Date now = new Date();
    return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
            .setSubject(email)
            .setHeaderParam("tokenType", type)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + time))
            .compact();
  }

  @Transactional
  public String createRefreshToken(String email) {
    if (refreshTokenRepository.existsByEmail(email)) {
      throw new RefreshTokenException("Refresh 토큰을 만들려하는데 이미 만들어져있었다.");
    }
    String token = createToken(email, REFRESH_TOKEN, jwtProperties.getRefreshTime());
    refreshTokenRepository.save(
            new RefreshToken(token, email)
    );
    return token;
  }


  public String resolveToken(HttpServletRequest request) { // Authorization : Bearer <JWT> 에서 <JWT> 앞부분 삭제해서 반환
    String bearer = request.getHeader("Authorization");
    return ParseToken(bearer);
  }

  private String ParseToken(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) { // JWT 토큰 형식 올바른지 확인 후 앞부분 삭제
      return bearerToken.replace("Bearer ", "");
    }
    return null;
  }

  public UsernamePasswordAuthenticationToken authorization(String token) { // 토큰화
    UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
    log.warn(userDetails.getAuthorities().toString());
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }

  private String getTokenSubject(String token) {
    return getTokenBody(token).getSubject();
  }

  private Claims getTokenBody(String token) {
    try {
      return Jwts.parser()
              .setSigningKey(jwtProperties.getSecretKey())
              .build()
              .parseSignedClaims(token)
              .getPayload();
    } catch (Exception e) {
      e.printStackTrace();
    }
  return null;
  }
}
