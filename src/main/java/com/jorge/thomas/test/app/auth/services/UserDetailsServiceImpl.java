package com.jorge.thomas.test.app.auth.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.auth.models.User;
import com.jorge.thomas.test.app.auth.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = this.userRepository.findFirstByUsername(username);
    if(!optionalUser.isPresent()){
      new UsernameNotFoundException("Not found username");
    }

    User user = optionalUser.get();
    Set<GrantedAuthority> authorities = user
        .getRoles()
        .stream()
        .collect(Collectors.toSet());

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getEncodedPassword(),
        authorities);
  }

}
