package com.travelapp.backend.util;

import com.travelapp.backend.authentication.filter.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@UtilityClass
public class JwtUtilsUser {
    public static String extractJwtUsername(String token) {
        return extractJwtClaim(token, Claims::getSubject);
    }

    static Date extractJwtExpiration(String token) {
        return extractJwtClaim(token, Claims::getExpiration);
    }

    static <T> T extractJwtClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllJwtClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllJwtClaims(String token) {
        return Jwts.parser().setSigningKey(JwtConstant.JWT_SECRET).parseClaimsJws(token).getBody();
    }

    private static Boolean isJwtTokenExpired(String token) {
        return extractJwtExpiration(token).before(new Date());
    }

    public static String generateJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return JwtConstant.JWT_PREFIX + createJwtToken(claims, username);
    }

    private static String createJwtToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SECRET).compact();
    }

    public static Boolean validateJwtToken(String token, UserDetails userDetails) {
        final String username = extractJwtUsername(token);
        return (username.equals(userDetails.getUsername()) && !isJwtTokenExpired(token));
    }
}