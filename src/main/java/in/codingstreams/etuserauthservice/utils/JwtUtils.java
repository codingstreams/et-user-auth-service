package in.codingstreams.etuserauthservice.utils;

import in.codingstreams.etuserauthservice.constant.LoggingConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {

  public static final String ISSUER = "ET_USER_AUTH_SERVICE";
  private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

  private JwtUtils() {
  }

  public static String generateAccessToken(String email) {
    var methodName = "JwtUtils:generateAccessToken";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, email);

    var issuedAt = new Date();
    var expiration = DateUtils.addMinutes(issuedAt, 15);

    var accessToken = Jwts.builder()
        .issuer(ISSUER)
        .subject(email)
        .id(UUID.randomUUID().toString())
        .issuedAt(issuedAt)
        .expiration(expiration)
        .signWith(SECRET_KEY)
        .compact();

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return accessToken;
  }

  public static Optional<String> getUsernameFromToken(String accessToken) {
    var methodName = "JwtUtils:getUsernameFromToken";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, accessToken);

    var usernameOptional = parseToken(accessToken)
        .map(Claims::getSubject);

    log.info(LoggingConstants.END_METHOD_LOG, methodName);

    return usernameOptional;
  }

  private static Optional<Claims> parseToken(String accessToken) {
    var methodName = "JwtUtils:parseToken";
    log.info(LoggingConstants.START_METHOD_LOG, methodName, accessToken);

    var jwtParser = Jwts.parser()
        .verifyWith(SECRET_KEY)
        .build();

    try {
      log.info(LoggingConstants.END_METHOD_LOG, methodName);

      return Optional.of(
          jwtParser.parseSignedClaims(accessToken)
              .getPayload()
      );
    } catch (JwtException | IllegalArgumentException e) {
      log.error(LoggingConstants.ERROR_METHOD_LOG, methodName, "Error while parsing token.");

      return Optional.empty();
    }
  }
}
