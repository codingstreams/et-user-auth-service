package in.codingstreams.etuserauthservice.service.user;

import in.codingstreams.etuserauthservice.constant.ErrorMessages;
import in.codingstreams.etuserauthservice.constant.LoggingConstants;
import in.codingstreams.etuserauthservice.data.model.AppUser;
import in.codingstreams.etuserauthservice.data.repo.AppUserRepo;
import in.codingstreams.etuserauthservice.exception.BadCredentialsException;
import in.codingstreams.etuserauthservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
  private final AppUserRepo appUserRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public AppUser getUserInfo(String userId) {
    var methodName = "UserServiceImpl:getUserInfo";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, userId);

    // Find user by userId
    var appUser = appUserRepo.findById(userId)
        .orElseThrow(() -> {
          log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, userId + " not found.");

          return new UserNotFoundException(
              ErrorMessages.USER_NOT_FOUND.getErrorMessage(),
              ErrorMessages.USER_NOT_FOUND.getErrorCode()
          );
        });

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return appUser;
  }

  @Override
  public void changePassword(String userId, String oldPassword, String newPassword) {
    var methodName = "UserServiceImpl:changePassword";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, userId);

    // Find user by userId
    var appUser = getUserInfo(userId);

    // Check old password
    if(!passwordEncoder.matches(oldPassword, appUser.getPassword())){
      log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, "Incorrect password");

      throw  new BadCredentialsException(
          ErrorMessages.PASSWORD_NOT_MATCHED.getErrorMessage(),
          ErrorMessages.PASSWORD_NOT_MATCHED.getErrorCode()
      );
    }

    // Set new password
    appUser.setPassword(passwordEncoder.encode(newPassword));

    // Save user
    appUserRepo.save(appUser);

    log.info(LoggingConstants.END_METHOD_LOG, methodName);
  }

  @Override
  public AppUser updateName(String userId, String name) {
    var methodName = "UserServiceImpl:updateName";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, userId);

    // Find user by userId
    var appUser = getUserInfo(userId);

    // Update name
    appUser.setName(name);

    // Save user
    var saved = appUserRepo.save(appUser);

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return saved;
  }

  @Override
  public AppUser updateEmail(String userId, String email) {
    var methodName = "UserServiceImpl:updateEmail";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, userId);

    // Find user by userId
    var appUser = getUserInfo(userId);

    // Update name
    appUser.setEmail(email);

    // Save user
    var saved = appUserRepo.save(appUser);

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return saved;
  }
}
