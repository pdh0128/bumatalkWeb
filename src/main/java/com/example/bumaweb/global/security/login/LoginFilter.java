package com.example.bumaweb.global.security.login;

import com.example.bumaweb.global.security.jwt.JwtTokenProvider;
import com.example.bumaweb.global.security.login.dto.UserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final ObjectMapper objectMapper;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
    try {
      UserLoginDto userLoginDto = objectMapper.readValue(request.getInputStream(), UserLoginDto.class);
      String email = userLoginDto.getEmail();
      String password = userLoginDto.getPassword();
      UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
      this.setDetails(request, authRequest);
      return authenticationManager.authenticate(authRequest);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @Transactional
  @Override
  public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
    // 로그인 성공 -> JWT 발급
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String email = userDetails.getUsername();
    String accessToken = jwtTokenProvider.createAccessToken(email);
    String refreshToken = jwtTokenProvider.createRefreshToken(email);
    response.setHeader("access", accessToken);
    response.addCookie(createCookie("refresh", refreshToken));
    response.setStatus(HttpStatus.OK.value());
  }
  private Cookie createCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(24 * 60 * 60);
    cookie.setHttpOnly(true);
    return cookie;
  }
  @Override
  public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException faild) {
    // 로그인 실패
    faild.printStackTrace();
  }
}
