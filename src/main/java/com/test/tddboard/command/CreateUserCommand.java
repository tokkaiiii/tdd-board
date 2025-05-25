package com.test.tddboard.command;

public record CreateUserCommand(
    String email,
    String username,
    String password
) {}
