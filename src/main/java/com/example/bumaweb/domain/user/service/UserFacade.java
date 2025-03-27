package com.example.bumaweb.domain.user.service;

import com.example.bumaweb.domain.user.presentation.dto.req.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
  private final BCryptPasswordEncoder encoder;
  private final UserService service;

  public void createUser(UserCreateRequest request) {
    service.createUser(request, encoder.encode(request.password()));
  }
}
