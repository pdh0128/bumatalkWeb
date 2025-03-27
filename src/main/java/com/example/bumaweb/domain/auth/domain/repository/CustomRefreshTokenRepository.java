package com.example.bumaweb.domain.auth.domain.repository;


public interface CustomRefreshTokenRepository {
  boolean existsByEmail(String email);
}
