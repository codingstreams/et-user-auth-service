package in.codingstreams.etuserauthservice.handler;

import in.codingstreams.etuserauthservice.controller.dto.ErrorResponse;
import in.codingstreams.etuserauthservice.exception.BadCredentialsException;
import in.codingstreams.etuserauthservice.exception.InvalidTokenException;
import in.codingstreams.etuserauthservice.exception.UserAlreadyExistsException;
import in.codingstreams.etuserauthservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(
            ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(
            ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(
            ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build()
        );
  }

  @ExceptionHandler(InvalidTokenException.class)
  public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(
            ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build()
        );
  }
}
