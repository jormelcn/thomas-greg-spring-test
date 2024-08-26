package com.jorge.thomas.test.app.auth.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserWithSameUsernameAlreadyExist extends RuntimeException {

}
