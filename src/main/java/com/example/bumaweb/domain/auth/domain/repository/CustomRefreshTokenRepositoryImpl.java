package com.example.bumaweb.domain.auth.domain.repository;

import com.example.bumaweb.domain.auth.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomRefreshTokenRepositoryImpl implements CustomRefreshTokenRepository{
  private final RedisTemplate<String, RefreshToken> redisTemplate;

  @Override
  public boolean existsByEmail(String email) {
    Cursor<byte[]> cursor = redisTemplate.execute(
            (RedisCallback<Cursor<byte[]>>) connection -> connection.scan(ScanOptions.scanOptions().build()));

    try {
      while (cursor.hasNext()) {
        byte[] key = cursor.next();
        String keyString = new String(key);

        if (redisTemplate.opsForSet().isMember(keyString, email)) {
          return true;
        }
      }
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }
    return false;
  }
}
