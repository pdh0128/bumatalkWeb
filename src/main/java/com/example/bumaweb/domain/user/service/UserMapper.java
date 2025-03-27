package com.example.bumaweb.domain.user.service;

import com.example.bumaweb.domain.user.domain.User;
import com.example.bumaweb.domain.user.presentation.dto.req.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class UserMapper {
  public User toUser(UserCreateRequest request, String encodingPassword) {
    return User.builder()
            .username(request.username())
            .email(request.email())
            .password(encodingPassword)
            .role("ROLE_USER") // 무조건 유저
            .build();
  }
}
