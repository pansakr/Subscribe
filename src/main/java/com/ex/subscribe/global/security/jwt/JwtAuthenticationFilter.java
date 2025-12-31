package com.ex.subscribe.global.security.jwt;

import com.ex.subscribe.global.exception.BusinessErrorCode;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String authError = "AuthError";

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * true : 필터 실행 안함
     * false : 필터 실행
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        return !path.startsWith("/api/")
                || ("POST".equalsIgnoreCase(method) && path.equals("/api/users"))
                || ("GET".equalsIgnoreCase(method) && path.equalsIgnoreCase("/api/users/exists"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenProvider.resolveFromCookie(request, "ACCESS_TOKEN");

        if(token != null){
            TokenStatus status = jwtTokenProvider.validate(token);

            if (status == TokenStatus.VALID){

                Claims claims = jwtTokenProvider.parserClaimsJws(token);
                Authentication auth = jwtTokenProvider.toAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(auth);

            }else if(status == TokenStatus.EXPIRED){
                request.setAttribute(authError, BusinessErrorCode.TOKEN_EXPIRED);
            }else{
                request.setAttribute(authError, BusinessErrorCode.AUTH_REQUIRED);
            }
        }

        filterChain.doFilter(request, response);

    }
}
