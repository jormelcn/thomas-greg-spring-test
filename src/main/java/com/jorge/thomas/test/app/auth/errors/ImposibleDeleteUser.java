package com.jorge.thomas.test.app.auth.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ImposibleDeleteUser extends RuntimeException {

}
