package in.codingstreams.etuserauthservice.utils;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {

  public static final String ET_USER_AUTH_SERVICE = "ET_USER_AUTH_SERVICE";
  private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

  private JwtUtils() {
  }


  public static String generateAccessToken(String email) {
    var issuedAt = new Date();
    var expiration = DateUtils.addMinutes(issuedAt, 15);

    return Jwts.builder()
        .id(UUID.randomUUID().toString())
        .issuer(ET_USER_AUTH_SERVICE)
        .issuedAt(issuedAt)
        .expiration(expiration)
        .subject(email)
        .signWith(SECRET_KEY)
        .compact();
  }
}
