package in.codingstreams.etuserauthservice.controller.mapper;

import in.codingstreams.etuserauthservice.controller.model.UserInfoDto;
import in.codingstreams.etuserauthservice.service.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
  UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

  UserInfoDto toUserInfoDto(UserInfo userInfo);
}
