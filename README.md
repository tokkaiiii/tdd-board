# TDD 연습 - 게시판

"스프링 부트로 TDD를 연습하기 위한 게시판 애플리케이션입니다. 이 프로젝트는 TDD(테스트 주도 개발) 원칙을 적용하여 개발되었습니다."

## 응용프로그램 빌드

```bash
./gradlew build
```

## 응용프로그램 실행

```bash
./gradlew bootRun
```

## API 목록

### 게시판 회원 가입

요청
- 메서드: POST
- 경로: /board/sign-up
- 헤더
  ```
  Content-Type: application/json
  ```
- 본문
  ```
  CreateUserCommand {
    email: string, 
    username: string,
    password: string
  }
    ```
- curl 명령 예시
- ```bash
    curl -i -X POST 'http://localhost:8080/board/sign-up' \
    -H 'Content-Type: application/json' \
    -d '{
        "email": "user1@example.com"
        "username": "user1",
        "password": "user1-password"
    }'
    ```
성공 응답
- 상태코드: 204 No Content

정책
- 이메일 주소는 유일해야 한다
- 사용자이름은 3자 이상의 문자로 구성되어야 한다
- 비밀번호는 8자 이상의 문자로 구성되어야 한다

테스트
- [x] 올바르게 요청하면 204 No Content 상태코드를 반환한다
- [x] email 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] email 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [x] username 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [x] password 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다
- [ ] password 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다
- [ ] email 속성에 이미 존재하는 이메일 주소가 지정되면 400 Bad Request 상태코드를 반환한다
- [ ] 비밀번호를 올바르게 암호화한다      
