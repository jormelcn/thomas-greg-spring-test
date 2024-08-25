package com.jorge.thomas.test.app.auth.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminUpdateUserRequestDTO {
  String id;
  List<ScopedRoleDTO> roles;
}
