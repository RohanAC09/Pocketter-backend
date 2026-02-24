package com.rohan.post.config;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rohan.post.config.dto.auth.AuthPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Value("${secret.key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String jwt = header.substring(7);

            Claims claims = Jwts.parser()
            		.verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

            AuthPrincipal authPrincipal = new AuthPrincipal(claims.getSubject(), jwt);
            // String role = claims.get("role", String.class);

            // List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ADMIN"));

            Authentication auth =
                    new UsernamePasswordAuthenticationToken(
                    		authPrincipal, null, null);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
    
    public SecretKey getSigningKey() {
    	return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}