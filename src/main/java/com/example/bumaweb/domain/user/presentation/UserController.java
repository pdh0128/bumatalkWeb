package com.example.bumaweb.domain.user.presentation;

import com.example.bumaweb.domain.user.presentation.dto.req.UserCreateRequest;
import com.example.bumaweb.domain.user.service.UserFacade;
import com.example.bumaweb.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserFacade facade;
  @PostMapping("/signup")
  public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) {
    facade.createUser(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
