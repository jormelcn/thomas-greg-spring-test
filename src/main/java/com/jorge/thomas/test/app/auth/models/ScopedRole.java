package com.jorge.thomas.test.app.auth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScopedRole {
  @NonNull
  private String scope;

  @NonNull
  private String value;
}
