package com.example.bumaweb.domain.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value="refreshToken", timeToLive = 2592000L)
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id; // @Id의 대상 컬럼명이 id가 아니면 오류가 남. -> 왜 ? @Id는 Redis쪽에서 지원해주는 것이 아닌데 빈 설정과 뭔 관련이 있지?
//  @Id
  private String token;

  private String email;

  public RefreshToken(String token, String email) {
    this.token = token;
    this.email = email;
  }
}
