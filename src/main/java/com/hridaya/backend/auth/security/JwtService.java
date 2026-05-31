package com.hridaya.backend.auth.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;

import java.util.Date;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

@Service
public class JwtService {

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(
                    "mySuperSecretKeyForJwtAuthentication1234567890"
                            .getBytes()
            );

    public String generateToken(Long userId) {

        return Jwts.builder()

                .subject(String.valueOf(userId))

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )

                .signWith(SECRET_KEY)

                .compact();
    }


    public Long extractUserId(String token) {

        Claims claims = Jwts.parser()

                .verifyWith(SECRET_KEY)

                .build()

                .parseSignedClaims(token)

                .getPayload();

        return Long.parseLong(
                claims.getSubject()
        );
    }

    public boolean isTokenValid(String token) {

        try {

            Jwts.parser()

                    .verifyWith(SECRET_KEY)

                    .build()

                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }


}