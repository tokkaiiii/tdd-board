package com.test.tddboard.api.writer.signup;

import static org.assertj.core.api.Assertions.assertThat;

import com.test.tddboard.TddBoardApplication;
import com.test.tddboard.command.CreateUserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(
    classes = TddBoardApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DisplayName("POST /writer/sign-up")
class POST_specs {

  @DisplayName("올바르게 요청하면 204 No Content 상태코드를 반환한다")
  @Test
  void 올바르게_요청하면_204_No_Content_상태코드를_반환한다(
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        "user1@example.com",
        "user1",
        "password"
    );

    // Act
    ResponseEntity<Void> response = client.postForEntity("/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(204);
  }

  @DisplayName("email 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다")
  @Test
  void email_속성이_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        null,
        "user1",
        "password"
    );

    // Act
    ResponseEntity<Void> response = client.postForEntity("/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @DisplayName("email 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다")
  @ParameterizedTest
  @ValueSource(strings = {
      "invalid-email",
      "invalid-email@.com",
      "invalid-email@test",
      "invalid-email@test.",
      "invalid-email@test..com",
      "@example.com",
  })
  void email_속성이_올바른_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
      String email,
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        email,
        "user1",
        "password"
    );

    // Act
    ResponseEntity<Void> response = client.postForEntity("/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @DisplayName("username 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다")
  @Test
  void username_속성이_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        "user@test.com",
        null,
        "password"
    );

    // Act
    ResponseEntity<Void> response = client.postForEntity(
        "/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @DisplayName("username 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다")
  @ParameterizedTest
  @ValueSource(strings = {
      "",
      "us",
      "us ",
  })
  void username_속성이_올바른_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
      String username,
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        "user@test.com",
        username,
        "password"
    );

    // Act
    ResponseEntity<Void> response = client.postForEntity(
        "/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @DisplayName("password 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다")
  @Test
  void password_속성이_지정되지_않으면_400_Bad_Request_상태코드를_반환한다(
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        "user@test.com",
        "user",
        null);

    // Act
    ResponseEntity<Void> response = client.postForEntity(
        "/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @DisplayName("password 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다")
  @ParameterizedTest
  @ValueSource(strings = {
      "",
      "pass",
      "1234567",
      "1234567 ",
  })
  void password_속성이_올바른_형식을_따르지_않으면_400_Bad_Request_상태코드를_반환한다(
      String password,
      @Autowired TestRestTemplate client
  ) {
    // Arrange
    var command = new CreateUserCommand(
        "user@test.com",
        "user",
        password
    );

    // Act
    ResponseEntity<Void> response = client.postForEntity(
        "/writer/sign-up",
        command,
        Void.class);

    // Assert
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }
}
