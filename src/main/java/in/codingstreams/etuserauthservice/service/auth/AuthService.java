package in.codingstreams.etuserauthservice.service.auth;

import in.codingstreams.etuserauthservice.service.model.AuthRequest;
import in.codingstreams.etuserauthservice.service.model.AuthResponse;

public interface AuthService {
  AuthResponse signUp(AuthRequest authRequest);
}
