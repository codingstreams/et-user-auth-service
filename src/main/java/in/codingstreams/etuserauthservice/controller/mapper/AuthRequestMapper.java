package in.codingstreams.etuserauthservice.controller.mapper;

import in.codingstreams.etuserauthservice.controller.dto.AuthRequest;
import in.codingstreams.etuserauthservice.service.auth.model.LoginRequest;
import in.codingstreams.etuserauthservice.service.auth.model.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRequestMapper {
  AuthRequestMapper INSTANCE = Mappers.getMapper(AuthRequestMapper.class);

  SignUpRequest mapToSignUpRequest(AuthRequest authRequest);

  LoginRequest mapToLoginRequest(AuthRequest authRequest);
}
