package in.codingstreams.etuserauthservice.service.auth;

import in.codingstreams.etuserauthservice.data.model.AppUser;
import in.codingstreams.etuserauthservice.data.repo.AppUserRepo;
import in.codingstreams.etuserauthservice.exception.InvalidTokenException;
import in.codingstreams.etuserauthservice.exception.UserAlreadyExistsException;
import in.codingstreams.etuserauthservice.service.model.AuthRequest;
import in.codingstreams.etuserauthservice.service.model.AuthResponse;
import in.codingstreams.etuserauthservice.service.model.VerifyTokenRequest;
import in.codingstreams.etuserauthservice.service.model.VerifyTokenResponse;
import in.codingstreams.etuserauthservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static in.codingstreams.etuserauthservice.constant.LoggingConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
  private final AppUserRepo appUserRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public AuthResponse signUp(AuthRequest authRequest) {
    log.info(METHOD_LOG_START, "signUp");

    // Check whether user exists or not
    if(appUserRepo.existsByEmail(authRequest.getEmail())){
      log.error(METHOD_LOG_ERROR, "signUp", "UserAlreadyExistsException");
      throw new UserAlreadyExistsException("User already exists!");
    }

    // Encode password
    var encodedPassword = passwordEncoder.encode(authRequest.getPassword());

    // Create app user
    var appUser = AppUser.builder()
        .name(authRequest.getName())
        .email(authRequest.getEmail())
        .password(encodedPassword)
        .build();

    // save user
    appUserRepo.save(appUser);

    // return AuthResponse
    var accessToken = JwtUtils.generateAccessToken(authRequest.getEmail());

    log.info(METHOD_LOG_END, "signUp");

    return AuthResponse.builder()
        .accessToken(accessToken)
        .build();
  }

  @Override
  public AuthResponse login(AuthRequest authRequest) {
    log.info(METHOD_LOG_START, "login");

    // Get user by email/username
    var appUser = appUserRepo.findByEmail(authRequest.getEmail())
        .orElseThrow(() -> {
          log.error(METHOD_LOG_ERROR, "login", "UsernameNotFoundException");

          return new UsernameNotFoundException(authRequest.getEmail() + " not found!");
        });

    // Check passwords
    if(passwordEncoder.matches(authRequest.getPassword(), appUser.getPassword())){
      // return AuthResponse
      var accessToken = JwtUtils.generateAccessToken(authRequest.getEmail());

      log.info(METHOD_LOG_END, "login");

      return AuthResponse.builder()
          .accessToken(accessToken)
          .build();
    }

    log.error(METHOD_LOG_ERROR, "login", "BadCredentialsException");
    throw new BadCredentialsException("Password not matched!");
  }

  @Override
  public VerifyTokenResponse verifyToken(VerifyTokenRequest verifyTokenRequest) {
    log.info(METHOD_LOG_START, "verifyToken");
    var usernameOptional = JwtUtils.getUsernameFromToken(verifyTokenRequest.getAccessToken());

    if(usernameOptional.isEmpty()){
      log.error(METHOD_LOG_ERROR, "verifyToken", "InvalidTokenException");
      throw new InvalidTokenException("Invalid Access Token");
    }

    var appUser = appUserRepo.findByEmail(usernameOptional.get())
        .orElseThrow(() -> {
          log.error(METHOD_LOG_ERROR, "verifyToken", "InvalidTokenException");
          return new InvalidTokenException("Invalid Access Token");
        });

    log.info(METHOD_LOG_END, "verifyToken");
    return VerifyTokenResponse.builder()
        .userId(appUser.getUserId())
        .build();
  }
}
