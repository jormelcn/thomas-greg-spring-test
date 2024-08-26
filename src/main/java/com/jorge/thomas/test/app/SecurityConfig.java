package com.jorge.thomas.test.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.jorge.thomas.test.app.auth.reqfilters.JWTRequestFilter;
import com.jorge.thomas.test.app.auth.services.JWTService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Bean
  UserDetailsService userDetailsService(UserDetailsService userDetailsService) {
    return userDetailsService;
  }

  @Bean
  static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(
      AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  SecurityFilterChain securityFilterChain(
      AuthenticationManager authenticationManager,
      JWTService jwtService,
      HttpSecurity http,
      HandlerExceptionResolver handlerExceptionResolver,
      AuthenticationProvider authenticationProvider)
      throws Exception {
    http
        .csrf(x -> x.disable())
        .authorizeHttpRequests(x -> x
            .requestMatchers("/error").permitAll()
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/v3/api-docs/**").permitAll()
            .anyRequest().authenticated())
        .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(
            new JWTRequestFilter(
                jwtService,
                handlerExceptionResolver),
            UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
