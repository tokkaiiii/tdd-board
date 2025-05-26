package com.test.tddboard.api.controller;

import com.test.tddboard.command.CreateUserCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WriterSignupController {

  public static final String EMAIL_REGEX = "^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  @PostMapping("/writer/sign-up")
  public ResponseEntity<?> signUp(@RequestBody CreateUserCommand command) {

    if (isCommandValid(command)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.badRequest().build();

  }

  private static boolean isCommandValid(CreateUserCommand command) {
    return isEmailValid(command.email())
        && isUsernameValid(command.username())
        && isPasswordValid(command.password());
  }

  private static boolean isPasswordValid(String password) {
    return password != null
        && password.trim().length() >= 8;
  }

  private static boolean isUsernameValid(String username) {
    return username != null
        && username.trim().length() >= 3;
  }

  private static boolean isEmailValid(String email) {
    return email != null
        && email.matches(EMAIL_REGEX);
  }

}
