package com.test.tddboard.api.writer.signup;

import static org.assertj.core.api.Assertions.assertThat;

import com.test.tddboard.TddBoardApplication;
import com.test.tddboard.command.CreateUserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
