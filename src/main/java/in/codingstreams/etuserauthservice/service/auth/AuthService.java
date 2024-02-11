package in.codingstreams.etuserauthservice.service.auth;

import in.codingstreams.etuserauthservice.service.model.AuthRequest;
import in.codingstreams.etuserauthservice.service.model.AuthResponse;
import in.codingstreams.etuserauthservice.service.model.VerifyTokenRequest;
import in.codingstreams.etuserauthservice.service.model.VerifyTokenResponse;

public interface AuthService {
  AuthResponse signUp(AuthRequest authRequest);

  AuthResponse login(AuthRequest authRequest);

  VerifyTokenResponse verifyToken(VerifyTokenRequest verifyTokenRequest);
}
