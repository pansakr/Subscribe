package com.ex.subscribe.global.security.oauth2;

import com.ex.subscribe.user.entity.ProviderType;
import com.ex.subscribe.user.entity.UserEntity;
import com.ex.subscribe.user.entity.UserRole;
import com.ex.subscribe.user.entity.UserStatus;
import com.ex.subscribe.user.repository.UserQueryRepository;
import com.ex.subscribe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends OidcUserService {

    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;


    /**
     * 소셜 로그인
     */
    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oidcUser.getSubject();
        String email = oidcUser.getEmail();

        // registrationId 값과 일치하는 ProviderType 반환 (registrationId 의 대문자 일관성 유지)
        ProviderType providerType = ProviderType.find(registrationId)
                .orElseThrow(() -> new OAuth2AuthenticationException(
                        new OAuth2Error(OAuth2Status.UNSUPPORTED_PROVIDER.name())));

        UserEntity userEntity = userRepository.findByProviderAndProviderId(providerType, providerId)
                .orElseGet(() -> {
                    if (email == null || email.isBlank())
                        throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2Status.EMAIL_REQUIRED.name()));

                    if (userQueryRepository.existsByEmail(email))
                        throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2Status.EMAIL_DUPLICATED.name()));

                    return userRepository.save(
                            new UserEntity(email,
                                    UserRole.ROLE_USER,
                                    UserStatus.ACTIVE,
                                    providerType,
                                    providerId)
                    );
                });

        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(userEntity.getRole().name()));

        return new OidcUserPrincipal(authorities,
                oidcUser.getIdToken(),
                oidcUser.getUserInfo(), // todo : getUserInfo = null 해결 필요
                userEntity.getId(),
                List.of(userEntity.getRole().name()));
    }
}
