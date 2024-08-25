package com.jorge.thomas.test.app.auth.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.lib.DTOMapper;
import com.jorge.thomas.test.app.auth.errors.ImposibleDeleteUser;
import com.jorge.thomas.test.app.auth.errors.NotFoundUser;
import com.jorge.thomas.test.app.auth.models.User;
import com.jorge.thomas.test.app.auth.repository.UserRepository;

import kotlin.NotImplementedError;

@Service
@PreAuthorize("hasAnyAuthority('users::*', 'users::delete')")
public class AdminDeleteUser {

  @Autowired
  UserRepository userRepository;

  @Autowired
  DTOMapper dtoMapper;

  public void perform(String id) {
    User user = this.userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundUser());

    if(this.thereAreActiveRelations(user)) {
      throw new ImposibleDeleteUser();
    }

    this.userRepository.delete(user);
  }

  private boolean thereAreActiveRelations(User user) {
    throw new NotImplementedError();
  }

}
