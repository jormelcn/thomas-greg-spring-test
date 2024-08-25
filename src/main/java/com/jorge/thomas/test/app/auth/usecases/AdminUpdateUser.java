package com.jorge.thomas.test.app.auth.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.auth.dtos.AdminUpdateUserRequestDTO;
import com.jorge.thomas.test.app.auth.dtos.UserForAdminDTO;
import com.jorge.thomas.test.app.auth.errors.NotFoundUser;
import com.jorge.thomas.test.app.auth.models.ScopedRole;
import com.jorge.thomas.test.app.auth.models.User;
import com.jorge.thomas.test.app.auth.repository.UserRepository;
import com.jorge.thomas.test.app.lib.DTOMapper;

@Service
@PreAuthorize("hasAnyAuthority('users::*', 'users::update')")
public class AdminUpdateUser {

  @Autowired
  UserRepository userRepository;

  @Autowired
  DTOMapper dtoMapper;

  public UserForAdminDTO perform(AdminUpdateUserRequestDTO request) {
    User user = this.userRepository
        .findById(request.getId())
        .orElseThrow(() -> new NotFoundUser());

    if (request.getRoles() != null)
      user.setRoles(this.dtoMapper.mapList(request.getRoles(), ScopedRole.class));

    this.userRepository.save(user);
    return this.dtoMapper.map(user, UserForAdminDTO.class);
  }

}
