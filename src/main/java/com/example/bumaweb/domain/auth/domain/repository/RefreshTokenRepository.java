package com.example.bumaweb.domain.auth.domain.repository;

import com.example.bumaweb.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String>, CustomRefreshTokenRepository {
  boolean existsByEmail(String email);
}
