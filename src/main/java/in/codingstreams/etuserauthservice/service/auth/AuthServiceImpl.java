package in.codingstreams.etuserauthservice.service.auth;

import in.codingstreams.etuserauthservice.constant.ErrorMessages;
import in.codingstreams.etuserauthservice.constant.LoggingConstants;
import in.codingstreams.etuserauthservice.data.model.AppUser;
import in.codingstreams.etuserauthservice.data.repo.AppUserRepo;
import in.codingstreams.etuserauthservice.exception.BadCredentialsException;
import in.codingstreams.etuserauthservice.exception.InvalidTokenException;
import in.codingstreams.etuserauthservice.exception.UserAlreadyExistsException;
import in.codingstreams.etuserauthservice.exception.UserNotFoundException;
import in.codingstreams.etuserauthservice.service.auth.model.LoginRequest;
import in.codingstreams.etuserauthservice.service.auth.model.SignUpRequest;
import in.codingstreams.etuserauthservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
  private final AppUserRepo appUserRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public String signUp(SignUpRequest signUpRequest) {
    var methodName = "AuthServiceImpl:signUp";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, signUpRequest);

    // Check whether user already exists or not using email
    if (appUserRepo.existsByEmail(signUpRequest.getEmail())) {
      log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, signUpRequest.getEmail() + " already exists.");

      throw new UserAlreadyExistsException(
          ErrorMessages.USER_ALREADY_EXISTS.getErrorMessage(),
          ErrorMessages.USER_ALREADY_EXISTS.getErrorCode()
      );
    }

    // Create App User model
    var appUser = AppUser.builder()
        .name(signUpRequest.getName())
        .email(signUpRequest.getEmail())
        .password(passwordEncoder.encode(signUpRequest.getPassword()))
        .build();

    // Save user
    appUserRepo.save(appUser);

    // Generate access token
    var accessToken = JwtUtils
        .generateAccessToken(signUpRequest.getEmail());

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    // Return access token
    return accessToken;
  }

  @Override
  public String login(LoginRequest loginRequest) {
    var methodName = "AuthServiceImpl:login";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, loginRequest);

    // Find user by email
    var appUser = appUserRepo.findByEmail(loginRequest.getEmail())
        .orElseThrow(() -> {
          log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, loginRequest.getEmail() + " not found.");

          return new UserNotFoundException(
              ErrorMessages.USER_NOT_FOUND.getErrorMessage(),
              ErrorMessages.USER_NOT_FOUND.getErrorCode()
          );
        });

    // Check passwords
    if(!passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())){
      log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, "Incorrect password");

      throw  new BadCredentialsException(
          ErrorMessages.PASSWORD_NOT_MATCHED.getErrorMessage(),
          ErrorMessages.PASSWORD_NOT_MATCHED.getErrorCode()
      );
    }

    // Generate access Token
    var accessToken = JwtUtils
        .generateAccessToken(loginRequest.getEmail());

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    // Return access token
    return accessToken;
  }

  @Override
  public String verifyToken(String accessToken) {
    var methodName = "AuthServiceImpl:verifyToken";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, accessToken);

    // Extract username(email) from access token
    var username = JwtUtils.getUsernameFromToken(accessToken)
        .orElseThrow(()-> {
          log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, "Invalid token.");

          return new InvalidTokenException(
              ErrorMessages.INVALID_ACCESS_TOKEN.getErrorMessage(),
              ErrorMessages.INVALID_ACCESS_TOKEN.getErrorCode()
          );
        });

    // Find user by email
    var appUser = appUserRepo.findByEmail(username)
        .orElseThrow(() -> {
          log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, username + " not found.");

          return new UserNotFoundException(
              ErrorMessages.USER_NOT_FOUND.getErrorMessage(),
              ErrorMessages.USER_NOT_FOUND.getErrorCode()
          );
        });

    // Return user id
    var userId = appUser.getUserId();

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return userId;
  }
}
