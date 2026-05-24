package com.ex.subscribe.global.security.oauth2;

import com.ex.subscribe.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OidcUserPrincipal principal = (OidcUserPrincipal) authentication.getPrincipal();

        String accessToken = jwtTokenProvider.createAccessToken(
                principal.getUserId(),
                principal.getEmail(),
                principal.getRoles());

        ResponseCookie cookie = ResponseCookie.from("ACCESS_TOKEN", accessToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(Duration.ofMinutes(30))
                .path("/")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        getRedirectStrategy().sendRedirect(request, response, "/");

    }
}
