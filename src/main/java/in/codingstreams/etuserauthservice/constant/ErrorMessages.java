package in.codingstreams.etuserauthservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
  USER_ALREADY_EXISTS("E409", "User already exists!"),
  USER_NOT_FOUND("E404", "User not found!"),
  PASSWORD_NOT_MATCHED("E401", "Password not matched!"),
  INVALID_ACCESS_TOKEN("T401", "Invalid access token!");

  String errorCode;
  String errorMessage;
}
