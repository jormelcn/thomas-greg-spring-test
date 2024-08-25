package com.jorge.thomas.test.app.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScopedRoleDTO {
  @NotNull
  @NotBlank
  String scope;

  @NotNull
  @NotBlank
  String value;
}
