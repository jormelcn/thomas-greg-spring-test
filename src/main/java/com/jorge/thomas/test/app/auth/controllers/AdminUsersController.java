package com.jorge.thomas.test.app.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.thomas.test.app.auth.dtos.AdminCreateUserRequestDTO;
import com.jorge.thomas.test.app.auth.dtos.UserForAdminDTO;
import com.jorge.thomas.test.app.auth.usecases.AdminCreateUser;
import com.jorge.thomas.test.app.auth.usecases.AdminDeleteUser;
import com.jorge.thomas.test.app.auth.usecases.AdminReadUser;
import com.jorge.thomas.test.app.auth.usecases.AdminUpdateUser;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.jorge.thomas.test.app.auth.dtos.AdminUpdateUserRequestDTO;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin()
@SecurityRequirement(name = "JWT")
@RequestMapping("/admin/users")
public class AdminUsersController {
  @Autowired
  AdminCreateUser adminCreateUser;

  @Autowired
  AdminUpdateUser adminUpdateUser;

  @Autowired
  AdminReadUser adminReadUser;

  @Autowired
  AdminDeleteUser adminDeleteUser;

  @PostMapping("")
  UserForAdminDTO create(@Valid @RequestBody AdminCreateUserRequestDTO request) {
    return this.adminCreateUser.perform(request);
  }

  @PutMapping("{id}")
  UserForAdminDTO update(@PathVariable String id,
      @Valid @RequestBody AdminUpdateUserRequestDTO request) {

    request.setId(id);
    return this.adminUpdateUser.perform(request);
  }

  @DeleteMapping("{id}")
  void delete(@PathVariable String id) {
    this.adminDeleteUser.perform(id);
  }

  @GetMapping("{id}")
  UserForAdminDTO read(@PathVariable String id) {
    return this.adminReadUser.perform(id);
  }
}
