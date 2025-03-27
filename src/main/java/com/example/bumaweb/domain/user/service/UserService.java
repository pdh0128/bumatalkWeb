package com.example.bumaweb.domain.user.service;

import com.example.bumaweb.domain.user.domain.User;
import com.example.bumaweb.domain.user.domain.repository.UserRepository;
import com.example.bumaweb.domain.user.exception.UserAlreadyExistsException;
import com.example.bumaweb.domain.user.presentation.dto.req.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final UserMapper mapper;

  public void createUser(UserCreateRequest request, String encodedPassword) { // 회원가입 ( 유저 생성 )
    if (repository.existsUserByEmail(request.email())) { // 중복 email 검증
      throw new UserAlreadyExistsException("User is already exists");
    }
    repository.save(mapper.toUser(request, encodedPassword));
  }

  public User findUserByEmail(String email) {
    return repository.findUserByEmail(email);
  }
}
