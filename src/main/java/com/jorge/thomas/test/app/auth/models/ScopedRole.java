package com.jorge.thomas.test.app.auth.models;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScopedRole implements GrantedAuthority {
  @NonNull
  private String scope;

  @NonNull
  private String value;

  @Override
  public String getAuthority() {
    return this.scope + "::" + this.value;
  }
}
