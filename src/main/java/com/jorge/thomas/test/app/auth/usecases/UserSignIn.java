package com.jorge.thomas.test.app.auth.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.auth.dtos.OwnUserDTO;
import com.jorge.thomas.test.app.auth.dtos.SuccessSignInDTO;
import com.jorge.thomas.test.app.auth.dtos.TokenWithExpirationDTO;
import com.jorge.thomas.test.app.auth.dtos.UserSignInRequestDTO;
import com.jorge.thomas.test.app.auth.errors.AuthenticationError;
import com.jorge.thomas.test.app.auth.models.TokenWithExpiration;
import com.jorge.thomas.test.app.auth.models.User;
import com.jorge.thomas.test.app.auth.repository.UserRepository;
import com.jorge.thomas.test.app.auth.services.JWTService;
import com.jorge.thomas.test.app.lib.DTOMapper;

@Service
public class UserSignIn {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JWTService jwtService;

  @Autowired
  DTOMapper dtoMapper;

  @Autowired
  UserDetailsService userDetailsService;

  public SuccessSignInDTO perform(UserSignInRequestDTO request) {
    Authentication authentication;
    try {
      authentication = this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    } catch (AuthenticationException e) {
      throw new AuthenticationError();
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = this.userRepository.findFirstByUsername(authentication.getName())
        .orElseThrow(() -> new BadCredentialsException(null));

    UserDetails userDetail = this.userDetailsService.loadUserByUsername(authentication.getName());
    TokenWithExpiration token = this.jwtService.generateToken(userDetail);

    return new SuccessSignInDTO(
        this.dtoMapper.map(user, OwnUserDTO.class),
        this.dtoMapper.map(token, TokenWithExpirationDTO.class));
  }

}
