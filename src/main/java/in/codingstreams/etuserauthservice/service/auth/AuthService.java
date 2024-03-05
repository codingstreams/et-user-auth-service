package in.codingstreams.etuserauthservice.service.auth;

import in.codingstreams.etuserauthservice.service.auth.model.LoginRequest;
import in.codingstreams.etuserauthservice.service.auth.model.SignUpRequest;

public interface AuthService {
  String signUp(SignUpRequest signUpRequest);

  String login(LoginRequest loginRequest);

  String verifyToken(String accessToken);
}
