package in.codingstreams.etuserauthservice.controller.mapper;

import in.codingstreams.etuserauthservice.controller.dto.AuthRequest;
import in.codingstreams.etuserauthservice.controller.dto.UserInfo;
import in.codingstreams.etuserauthservice.data.model.AppUser;
import in.codingstreams.etuserauthservice.service.auth.model.LoginRequest;
import in.codingstreams.etuserauthservice.service.auth.model.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
  UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

  UserInfo mapToUserInfo(AppUser appUser);
}
