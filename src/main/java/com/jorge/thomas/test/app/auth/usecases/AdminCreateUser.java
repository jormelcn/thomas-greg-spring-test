package com.jorge.thomas.test.app.auth.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.auth.dtos.AdminCreateUserRequestDTO;
import com.jorge.thomas.test.app.auth.dtos.UserForAdminDTO;
import com.jorge.thomas.test.app.auth.errors.UserWithSameUsernameAlreadyExist;
import com.jorge.thomas.test.app.auth.models.ScopedRole;
import com.jorge.thomas.test.app.auth.models.User;
import com.jorge.thomas.test.app.auth.repository.UserRepository;
import com.jorge.thomas.test.app.lib.DTOMapper;

@Service
@PreAuthorize("hasAuthority('users::create')")
public class AdminCreateUser {

  @Autowired
  private DTOMapper dtoMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserForAdminDTO perform(AdminCreateUserRequestDTO request) {
    Optional<User> userWithSameEmail = this.userRepository.findFirstByUsername(request.getEmail());
    if (userWithSameEmail.isPresent()) {
      throw new UserWithSameUsernameAlreadyExist();
    }

    List<ScopedRole> roles = this.dtoMapper.mapList(request.getRoles(), ScopedRole.class);

    User user = new User(request.getEmail(), this.passwordEncoder.encode(request.getPassword()), roles);
    this.userRepository.insert(user);
    return this.dtoMapper.map(user, UserForAdminDTO.class);
  }
}
