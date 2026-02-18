package com.rohan.authentication_service.service;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rohan.authentication_service.entity.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${secret.key}")
    private String secretKey;
    
    @Value("${token.validity.inMinutes}")
    private int timeInMinute;

    public String generateJwt(Account account) {
    	return Jwts.builder()
                .claims(new HashMap<>())
                .subject(account.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * timeInMinute))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isJwtValid(String jwt, Account account) {
    	Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        String username = claims.getSubject();
        boolean isJWTExpired = claims.getExpiration().before(new Date());
        return username.equals(account.getUsername()) && !isJWTExpired ;
    }

    public String getJwtUsername(String jwt) {
    	Claims claims=null;
		try {
			claims = Jwts.parser()
			        .verifyWith(getSigningKey())
			        .build()
			        .parseSignedClaims(jwt)
			        .getPayload();
		} catch (JwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean isJWTExpired = claims.getExpiration().before(new Date());
        String username = !isJWTExpired ? claims.getSubject() : null;
        return username ;
    }
    
    public SecretKey getSigningKey() {
    	return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
