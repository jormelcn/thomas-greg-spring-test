package com.jorge.thomas.test.app.auth.models;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class TokenWithExpiration {  
  @NonNull
  LocalDateTime expirationDate;
  
  @NonNull
  String Value;
}
