package com.jorge.thomas.test.app.auth.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TokenWithExpirationDTO {
  @NonNull
  @NotNull
  LocalDateTime expirationDate;

  @NonNull
  @NotNull
  String Value;
}
