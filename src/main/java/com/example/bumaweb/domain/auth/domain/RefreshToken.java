package com.example.bumaweb.domain.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value="refreshToken", timeToLive = 2592000L)
public class RefreshToken {
  @Id
  private String token;

  private String email;

  public RefreshToken(String token, String email) {
    this.token = token;
    this.email = email;
  }
}
