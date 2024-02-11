package in.codingstreams.etuserauthservice.controller.mapper;

import in.codingstreams.etuserauthservice.controller.model.VerifyTokenRequestDto;
import in.codingstreams.etuserauthservice.controller.model.VerifyTokenResponseDto;
import in.codingstreams.etuserauthservice.service.model.VerifyTokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VerifyTokenResponseMapper {
  VerifyTokenResponseMapper INSTANCE = Mappers.getMapper(VerifyTokenResponseMapper.class);

  VerifyTokenResponseDto toVerifyTokenResponseDto(VerifyTokenResponse tokenResponse);
}
