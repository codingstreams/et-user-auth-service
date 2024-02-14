package in.codingstreams.etuserauthservice.handler;

import in.codingstreams.etuserauthservice.controller.model.ErrorResponseDto;
import in.codingstreams.etuserauthservice.exception.InvalidTokenException;
import in.codingstreams.etuserauthservice.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
    var httpStatus = HttpStatus.CONFLICT;
    return ResponseEntity
        .status(httpStatus)
        .body(
            ErrorResponseDto.builder()
                .errorStatusCode(String.valueOf(httpStatus.value()))
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleUsernameNotFoundException(UsernameNotFoundException e) {
    var httpStatus = HttpStatus.NOT_FOUND;
    return ResponseEntity
        .status(httpStatus)
        .body(
            ErrorResponseDto.builder()
                .errorStatusCode(String.valueOf(httpStatus.value()))
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException e) {
    var httpStatus = HttpStatus.UNAUTHORIZED;
    return ResponseEntity
        .status(httpStatus)
        .body(
            ErrorResponseDto.builder()
                .errorStatusCode(String.valueOf(httpStatus.value()))
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(InvalidTokenException.class)
  public ResponseEntity<ErrorResponseDto> handleInvalidTokenException(InvalidTokenException e) {
    var httpStatus = HttpStatus.UNAUTHORIZED;
    return ResponseEntity
        .status(httpStatus)
        .body(
            ErrorResponseDto.builder()
                .errorStatusCode(String.valueOf(httpStatus.value()))
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
    var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    return ResponseEntity
        .status(httpStatus)
        .body(
            ErrorResponseDto.builder()
                .errorStatusCode(String.valueOf(httpStatus.value()))
                .errorMessage("Something went wrong!")
                .build()
        );
  }
}
