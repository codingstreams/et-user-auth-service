package in.codingstreams.etuserauthservice.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
  private String errorCode;

  public UserNotFoundException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
