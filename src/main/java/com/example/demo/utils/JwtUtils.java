package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils
{
    //    @Value("${jwt.secret}")
    String                  JWT_SECRET                    = "my_super_secure_jwt_secret_key_for_my_project_is_123456789";
    //    @Value("${refresh.secret}")
    String                  REFRESH_SECRET                = "my_super_secure_refresh_secret_key_for_my_project_is_123456789";
    //    @Value("${jwt.expiration}")
    long                    JWT_EXPIRATION_TIME           = 3600000;
    //    @Value("${jwt.refresh.token.expiration}")
    long                    REFRESH_TOKEN_EXPIRATION_TIME = 604800000;
    private final SecretKey accessKey                     = Keys
            .hmacShaKeyFor( JWT_SECRET.getBytes( StandardCharsets.UTF_8 ) );
    private final SecretKey refreshKey                    = Keys
            .hmacShaKeyFor( REFRESH_SECRET.getBytes( StandardCharsets.UTF_8 ) );
    public String generateToken( Long userId, String sessionId )
    {
        Date now = new Date();
        Date expiryDate = new Date( now.getTime() + JWT_EXPIRATION_TIME );
        Claims claims = Jwts.claims().subject( userId.toString() ).issuedAt( now ).expiration( expiryDate )
                .add( "sessionId", sessionId ).add( "type", "access" ).build();
        return Jwts.builder().claims( claims ).signWith( accessKey ).compact();
    }

    public String generateRefreshToken( Long userId, String sessionId )
    {
        Date now = new Date();
        Date expiryDate = new Date( now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME );
        Claims claims = Jwts.claims().subject( userId.toString() ).issuedAt( now ).expiration( expiryDate )
                .add( "sessionId", sessionId ).add( "type", "refresh" ).build();
        return Jwts.builder().claims( claims ).signWith( refreshKey ).compact();
    }

    public String generateSessionId()
    {
        return UUID.randomUUID().toString();
    }

    public Claims extractAccessClaims( String token )
    {
        return Jwts.parser().verifyWith( accessKey ).build().parseSignedClaims( token ).getPayload();
    }

    public Claims extractRefreshClaims( String token )
    {
        return Jwts.parser().verifyWith( refreshKey ).build().parseSignedClaims( token ).getPayload();
    }

    public String extractSubject( Claims claims )
    {
        return claims.getSubject();
    }

    public String extractSessionId( Claims claims )
    {
        return claims.get( "sessionId", String.class );
    }

    public boolean isTokenExpired( Claims claims )
    {
        return claims.getExpiration().before( new Date() );
    }
}
