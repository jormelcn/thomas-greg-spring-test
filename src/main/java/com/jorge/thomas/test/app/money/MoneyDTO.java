package com.jorge.thomas.test.app.money;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyDTO {
  @NotNull
  Double amount;

  @NotNull
  String currency;
}
