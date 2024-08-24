package com.jorge.thomas.test.app.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.thomas.test.app.auth.dtos.UserForAdminDTO;
import com.jorge.thomas.test.app.auth.usecases.AdminCreateUser;
import com.jorge.thomas.test.app.auth.usecases.AdminCreateUserRequestDTO;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/admin/users")
public class AdminUsersController {
  @Autowired
  AdminCreateUser adminCreateUser;

  @PostMapping("")
  UserForAdminDTO create(@Valid @RequestBody AdminCreateUserRequestDTO request) {
    return this.adminCreateUser.perform(request);
  }
}
