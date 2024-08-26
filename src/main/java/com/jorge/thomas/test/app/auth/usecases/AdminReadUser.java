package com.jorge.thomas.test.app.auth.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.auth.dtos.UserForAdminDTO;
import com.jorge.thomas.test.app.auth.errors.NotFoundUser;
import com.jorge.thomas.test.app.auth.models.User;
import com.jorge.thomas.test.app.auth.repository.UserRepository;

@Service
@PreAuthorize("hasAnyAuthority('users::*', 'users::read')")
public class AdminReadUser {

  @Autowired
  UserRepository userRepository;

  @Autowired
  DTOMapper dtoMapper;

  public UserForAdminDTO perform(String id) {
    User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundUser());
    return this.dtoMapper.map(user, UserForAdminDTO.class);
  }
}
