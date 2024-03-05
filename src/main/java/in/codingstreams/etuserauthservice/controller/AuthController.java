package in.codingstreams.etuserauthservice.controller;

import in.codingstreams.etuserauthservice.constant.LoggingConstants;
import in.codingstreams.etuserauthservice.controller.dto.AuthRequest;
import in.codingstreams.etuserauthservice.controller.dto.AuthResponse;
import in.codingstreams.etuserauthservice.controller.dto.VerifyTokenRequest;
import in.codingstreams.etuserauthservice.controller.dto.VerifyTokenResponse;
import in.codingstreams.etuserauthservice.controller.mapper.AuthRequestMapper;
import in.codingstreams.etuserauthservice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthService authService;

  //  Sign up
  @PostMapping("/sign-up")
  public ResponseEntity<AuthResponse> signUp(
      @RequestBody AuthRequest authRequest
  ) {
    var methodName = "AuthController:signUp";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, authRequest);

    var accessToken = authService.signUp(
        AuthRequestMapper.INSTANCE.mapToSignUpRequest(authRequest)
    );

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(
            AuthResponse.builder()
                .accessToken(accessToken)
                .build()
        );
  }

  //  Login
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(
      @RequestBody AuthRequest authRequest
  ) {
    var methodName = "AuthController:login";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, authRequest);

    var accessToken = authService.login(
        AuthRequestMapper.INSTANCE.mapToLoginRequest(authRequest)
    );

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            AuthResponse.builder()
                .accessToken(accessToken)
                .build()
        );
  }

  //  Verify Token
  @PostMapping("/verify-token")
  public ResponseEntity<VerifyTokenResponse> verifyToken(
      @RequestBody VerifyTokenRequest verifyTokenRequest
  ) {
    var methodName = "AuthController:verifyToken";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, verifyTokenRequest);

    var userId = authService
        .verifyToken(verifyTokenRequest.getAccessToken());

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            VerifyTokenResponse.builder()
                .userId(userId)
                .build()
        );
  }
}
