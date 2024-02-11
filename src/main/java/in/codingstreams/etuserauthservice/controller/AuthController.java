package in.codingstreams.etuserauthservice.controller;

import in.codingstreams.etuserauthservice.controller.mapper.AuthRequestMapper;
import in.codingstreams.etuserauthservice.controller.mapper.AuthResponseMapper;
import in.codingstreams.etuserauthservice.controller.mapper.VerifyTokenRequestMapper;
import in.codingstreams.etuserauthservice.controller.mapper.VerifyTokenResponseMapper;
import in.codingstreams.etuserauthservice.controller.model.AuthRequestDto;
import in.codingstreams.etuserauthservice.controller.model.AuthResponseDto;
import in.codingstreams.etuserauthservice.controller.model.VerifyTokenRequestDto;
import in.codingstreams.etuserauthservice.controller.model.VerifyTokenResponseDto;
import in.codingstreams.etuserauthservice.service.auth.AuthService;
import in.codingstreams.etuserauthservice.service.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static in.codingstreams.etuserauthservice.constant.LoggingConstant.METHOD_LOG_END;
import static in.codingstreams.etuserauthservice.constant.LoggingConstant.METHOD_LOG_START;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthService authService;
  @PostMapping("/sign-up")
  public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto){
    log.info(METHOD_LOG_START, "signUp");
    var authResponse = authService.signUp(AuthRequestMapper.INSTANCE.toAuthRequest(authRequestDto));

    log.info(METHOD_LOG_END, "signUp");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(AuthResponseMapper.INSTANCE.toAuthResponseDto(authResponse));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto){
    log.info(METHOD_LOG_START, "login");
    var authResponse = authService.login(AuthRequestMapper.INSTANCE.toAuthRequest(authRequestDto));

    log.info(METHOD_LOG_END, "login");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(AuthResponseMapper.INSTANCE.toAuthResponseDto(authResponse));
  }

  @PostMapping("/verify-token")
  public ResponseEntity<VerifyTokenResponseDto> verifyToken(@RequestBody VerifyTokenRequestDto verifyTokenRequestDto){
    log.info(METHOD_LOG_START, "verifyToken");
    var verifyTokenResponse = authService.verifyToken(
        VerifyTokenRequestMapper.INSTANCE.toVerifyTokenRequest(verifyTokenRequestDto)
    );

    log.info(METHOD_LOG_END, "verifyToken");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(VerifyTokenResponseMapper.INSTANCE.toVerifyTokenResponseDto(verifyTokenResponse));
  }
}
