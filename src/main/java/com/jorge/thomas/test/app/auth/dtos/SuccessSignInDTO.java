package com.jorge.thomas.test.app.auth.dtos;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SuccessSignInDTO {
  @NonNull
  OwnUserDTO user;

  @NonNull
  TokenWithExpirationDTO jwtToken;
}
