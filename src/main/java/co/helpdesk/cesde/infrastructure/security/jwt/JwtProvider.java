package co.helpdesk.cesde.infrastructure.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;


@Component
public class JwtProvider {

  @Value("${jwt.secret-key}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(
        secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(
      Long userId,
      String email,
      String rol) {

    Date now = new Date();

    Date expirationDate = new Date(now.getTime() + expiration);

    return Jwts.builder()
        .subject(email)
        .claim("userId", userId)
        .claim("rol", rol)
        .issuedAt(now)
        .expiration(expirationDate)
        .signWith(getSigningKey())
        .compact();
  }

  public String extractEmail(String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public Claims extractClaims(String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith((SecretKey) getSigningKey())
          .build()
          .parseClaimsJws(token);

      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
