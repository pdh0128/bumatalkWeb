package com.example.bumaweb.global.security.principle;

import com.example.bumaweb.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class AuthDetails implements UserDetails {
  private User user;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
   return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }
}
