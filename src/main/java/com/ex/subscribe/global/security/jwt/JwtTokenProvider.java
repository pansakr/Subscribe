package com.ex.subscribe.global.security.jwt;

import com.ex.subscribe.global.security.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.List;


@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.access-minutes}")
    private long accessTime;

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String createAccessToken(Long userId, String email, List<String> roles){
        Instant now = Instant.now();
        Instant exp = now.plus(Duration.ofMinutes(accessTime));

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("roles", roles)
                .claim("email", email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenStatus validate(String token){
        try {
            parserClaimsJws(token);
            return TokenStatus.VALID;
        }catch (ExpiredJwtException e){
            return TokenStatus.EXPIRED;
        }catch (JwtException | IllegalArgumentException e){
            return TokenStatus.INVALID;
        }
    }

    public Authentication toAuthentication(Claims claims){

        Long userId = Long.valueOf(claims.getSubject());
        List<String> roles = claims.get("roles", List.class);
        String email = claims.get("email", String.class);

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserPrincipal principal = new UserPrincipal(userId, email, roles, authorities);

        // UsernamePasswordAuthenticationToken 말고 다른 인증 객체는 안됨?
       return new UsernamePasswordAuthenticationToken(principal, claims, authorities);
    }

    public String resolveFromCookie(HttpServletRequest request, String cookieName){

        Cookie[] cookies = request.getCookies();

        if (cookies == null) return null;   // todo : return null 교체

        for (Cookie c : cookies){
            if (cookieName.equals(c.getName())) return c.getValue();
        }

        return null;
    }

    public Claims parserClaimsJws(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
