package in.codingstreams.etuserauthservice.controller;

import in.codingstreams.etuserauthservice.controller.mapper.UserInfoMapper;
import in.codingstreams.etuserauthservice.controller.model.UserInfoDto;
import in.codingstreams.etuserauthservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String userId){
    var userInfo = userService.getUserInfoByUserId(userId);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(UserInfoMapper.INSTANCE.toUserInfoDto(userInfo));
  }
}
