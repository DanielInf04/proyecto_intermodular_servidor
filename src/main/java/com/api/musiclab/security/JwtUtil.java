/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author danig
 */
public class JwtUtil {

    private static final String SECRET_KEY =
            "musiclab-secret-key-123456-musiclab-secret-key"; // mínimo 32 chars

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username, String email, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
