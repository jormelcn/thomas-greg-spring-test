package com.jorge.thomas.test.app.auth.usecases;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequestDTO {
  @NotNull
  @NotBlank
  String username;

  @NotNull
  @NotBlank
  String password;

  void setUsername(String username) {
    this.username = username.strip().toLowerCase();
  }
}
