package com.jorge.thomas.test.app.auth.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

@Data
@Document
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
  @Id
  private String id;

  @NonNull
  private String username;

  @NonNull
  private String encodedPassword;

  @NonNull
  private ArrayList<ScopedRole> roles;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
