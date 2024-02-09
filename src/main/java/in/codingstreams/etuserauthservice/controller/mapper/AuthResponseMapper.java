package in.codingstreams.etuserauthservice.controller.mapper;

import in.codingstreams.etuserauthservice.controller.model.AuthRequestDto;
import in.codingstreams.etuserauthservice.controller.model.AuthResponseDto;
import in.codingstreams.etuserauthservice.service.model.AuthRequest;
import in.codingstreams.etuserauthservice.service.model.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthResponseMapper {
  AuthResponseMapper INSTANCE = Mappers.getMapper(AuthResponseMapper.class);

  // Return type -> Target
  // Parameter -> Source
  AuthResponseDto toAuthResponseDto(AuthResponse authResponse);
}
