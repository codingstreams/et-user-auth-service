package in.codingstreams.etuserauthservice.service.user;

import in.codingstreams.etuserauthservice.data.repo.AppUserRepo;
import in.codingstreams.etuserauthservice.service.model.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
  private final AppUserRepo appUserRepo;
  @Override
  public UserInfo getUserInfoByUserId(String userId) {
   return appUserRepo.findByUserId(userId)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
  }
}
