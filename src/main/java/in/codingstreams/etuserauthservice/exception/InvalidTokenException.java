package in.codingstreams.etuserauthservice.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
  private String errorCode;

  public InvalidTokenException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
