package com.jorge.thomas.test.app.auth.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCreateUserRequestDTO {
  @NotNull
  @Email
  String email;

  @NotNull
  @NotBlank
  @Size(min = 8, max = 15)
  String password;

  @NotNull
  List<ScopedRoleDTO> roles;

  void setEmail(String email){
    this.email = email.strip().toLowerCase();
  }
}
