package com.ex.subscribe.global.security.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class OAuth2LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.info("oauth2 예외", exception);

        String messageKey = "login.failed";

        if (exception instanceof OAuth2AuthenticationException e){

            if (e.getError().getErrorCode().equals(OAuth2Status.EMAIL_REQUIRED.name())){
                messageKey = "login.email_required";
            } else if (e.getError().getErrorCode().equals(OAuth2Status.EMAIL_DUPLICATED.name())){
                messageKey = "login.email_duplicated";
            } else if (e.getError().getErrorCode().equals(OAuth2Status.UNSUPPORTED_PROVIDER.name())){
                messageKey = "login.unsupported_provider";
            }
        }

        getRedirectStrategy().sendRedirect(request, response, "/login?messageKey=" + messageKey);
    }
}
