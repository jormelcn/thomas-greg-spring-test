package com.jorge.thomas.test.app.auth.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForAdminDTO {
  String id;
  String username;
  List<ScopedRoleDTO> roles;
}
