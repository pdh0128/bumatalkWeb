package com.example.bumaweb.global.config;

import com.example.bumaweb.global.security.jwt.JwtTokenFilter;
import com.example.bumaweb.global.security.jwt.JwtTokenProvider;
import com.example.bumaweb.global.security.login.LoginFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable);
    http
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/", "/login", "/signup", "/board/**", "/auth/logout").permitAll()
//                    .requestMatchers("/auth/**").hasRole("USER")
                    .anyRequest().authenticated()
            );
    http
            .addFilterAt(new LoginFilter(authenticationManagerBean(authenticationConfiguration), new ObjectMapper(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    http
            .addFilterAfter(new JwtTokenFilter(jwtTokenProvider), LoginFilter.class);

    http
            .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }
}
