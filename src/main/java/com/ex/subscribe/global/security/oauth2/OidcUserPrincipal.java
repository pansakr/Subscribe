package com.ex.subscribe.global.security.oauth2;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;
import java.util.List;

@Getter
public class OidcUserPrincipal extends DefaultOidcUser {

    private final Long userId;
    private final String email;

    private final List<String> roles;

    public OidcUserPrincipal(Collection<? extends GrantedAuthority> authorities,
                             OidcIdToken idToken,
                             OidcUserInfo userInfo,
                             Long userId,
                             String email,
                             List<String> role) {
        super(authorities, idToken, userInfo);
        this.userId = userId;
        this.email = email;
        this.roles = role;
    }

}
