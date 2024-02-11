package in.codingstreams.etuserauthservice.controller.mapper;

import in.codingstreams.etuserauthservice.controller.model.AuthRequestDto;
import in.codingstreams.etuserauthservice.controller.model.VerifyTokenRequestDto;
import in.codingstreams.etuserauthservice.service.model.AuthRequest;
import in.codingstreams.etuserauthservice.service.model.VerifyTokenRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VerifyTokenRequestMapper {
  VerifyTokenRequestMapper INSTANCE = Mappers.getMapper(VerifyTokenRequestMapper.class);

  VerifyTokenRequest toVerifyTokenRequest(VerifyTokenRequestDto dto);
}
