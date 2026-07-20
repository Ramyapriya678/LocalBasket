package com.localbasket.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.expiration}")
    private long expiration;



    private SecretKey getSignKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());

    }



    public String generateToken(String email) {


        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                    new Date(System.currentTimeMillis() + expiration)
                )
                .signWith(getSignKey())
                .compact();

    }




    public String extractUsername(String token) {


        Claims claims = Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();


        return claims.getSubject();

    }



    public boolean isTokenValid(String token, String email) {

        return extractUsername(token).equals(email);

    }

}