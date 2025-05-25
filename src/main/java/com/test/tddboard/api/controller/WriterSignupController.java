package com.test.tddboard.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WriterSignupController {

  @PostMapping("/writer/sign-up")
  public ResponseEntity<?> signUp() {
    return ResponseEntity.noContent().build();
  }

}
