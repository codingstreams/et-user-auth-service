package in.codingstreams.etuserauthservice.service.user;

import in.codingstreams.etuserauthservice.service.model.UserInfo;

public interface UserService {

  UserInfo getUserInfoByUserId(String userId);
}
