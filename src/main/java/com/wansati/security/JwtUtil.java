package com.wansati.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;
import javax.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    private Key key;

    @PostConstruct
    public void init() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            key = Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            System.err.println("Error decoding JWT secret: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(key)
            .build() 
            .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}

