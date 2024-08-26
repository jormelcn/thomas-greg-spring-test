package com.jorge.thomas.test.app.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.thomas.test.app.auth.dtos.SuccessSignInDTO;
import com.jorge.thomas.test.app.auth.dtos.UserSignInRequestDTO;
import com.jorge.thomas.test.app.auth.usecases.UserSignIn;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin()
@RequestMapping("/auth")
public class AuthController {
  @Autowired
   private UserSignIn userSignIn;


  @PostMapping(path = "signin")
  SuccessSignInDTO signIn(@Valid @RequestBody UserSignInRequestDTO request) {
    return this.userSignIn.perform(request);
  }
}
