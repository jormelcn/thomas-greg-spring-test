package com.jorge.thomas.test.app.auth.services;

import java.security.Key;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jorge.thomas.test.app.auth.models.TokenWithExpiration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expiration-time}")
  private long jwtExpiration;

  public TokenWithExpiration generateToken(UserDetails userDetails) {
    Date now = new Date(System.currentTimeMillis());
    Date exp = new Date(System.currentTimeMillis() + this.jwtExpiration);
    String tokenValue = Jwts
        .builder()
        .subject(userDetails.getUsername())
        .issuedAt(now)
        .expiration(exp)
        .claim("auth", userDetails.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()))
        .signWith(getSignInKey())
        .compact();

    return new TokenWithExpiration(
        exp.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime(),
        tokenValue);
  }

  public UserDetails parseToken(String token) {
    Claims claims = Jwts.parser()
        .verifyWith((SecretKey) getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();

    String username = claims.getSubject();

    @SuppressWarnings("unchecked")
    List<GrantedAuthority> authorities = ((List<String>) claims.get("auth")).stream()
        .map(x -> new SimpleGrantedAuthority(x))
        .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(username,
        token,
        authorities);
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
