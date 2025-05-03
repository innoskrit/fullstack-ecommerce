package org.innoskrit.auth_lib;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.*;
import java.util.function.Function;
import java.security.Key;

public class JwtTokenUtil {

    // TODO: load from config
    private static final String SECRET_KEY = "uXZ4+XfYlZxzzmlEAWrBvuA2+gUAVrMj2t3GytRUxkz7CpxRpb0K9FXUB7lQ8eO8JgR2GZzNBiX2UJH+W4YXYA==";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(Long userId, List<String> roles) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    public Long extractUserId(String token) {
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}