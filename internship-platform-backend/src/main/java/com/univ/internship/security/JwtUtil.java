package com.univ.internship.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expireMillis;

    public JwtUtil(@Value("${app.jwt.secret}") String secret,
                   @Value("${app.jwt.expire-minutes}") long expireMinutes) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expireMillis = expireMinutes * 60 * 1000;
    }

    public String generateToken(String username, List<String> roles) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expireMillis))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }

    public String getUsername(String token) {
        return parse(token).getBody().getSubject();
    }
}
