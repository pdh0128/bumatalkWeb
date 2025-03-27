package com.example.bumaweb.global.security.principle;

import com.example.bumaweb.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
  private final UserService service;
  @Override
  public UserDetails loadUserByUsername(String email) {
    return new AuthDetails(service.findUserByEmail(email));
  }
}
