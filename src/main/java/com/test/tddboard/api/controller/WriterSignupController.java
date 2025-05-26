package com.test.tddboard.api.controller;

import com.test.tddboard.command.CreateUserCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WriterSignupController {

  @PostMapping("/writer/sign-up")
  public ResponseEntity<?> signUp(@RequestBody CreateUserCommand command) {
    String emailRegex = "^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    if (command.email() == null) {
      return ResponseEntity.badRequest().build();
    }else if (command.email().contains("@") == false) {
      return ResponseEntity.badRequest().build();
    } else if (command.email().matches(emailRegex) == false) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.noContent().build();
  }

}
