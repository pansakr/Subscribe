package com.ex.subscribe.user;

import com.ex.subscribe.global.exception.BusinessErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserQueryRepository userQueryRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    @DisplayName("회원가입 성공")
    void signUp_success() {

        // given
        UserRequest request = new UserRequest(
                "user@test.com", "123456", "testName", "testAddress", "01012345600");

        given(userQueryRepository.existsByEmail(request.getEmail())).willReturn(false);
        given(userQueryRepository.existsByPhone(request.getPhone())).willReturn(false);
        given(encoder.encode(anyString())).willReturn("encoded_pw");

        UserEntity userEntity = new UserEntity(
                request.getEmail(),
                "encoded_pw",
                request.getName(),
                request.getAddress(),
                request.getPhone(),
                UserRole.USER,
                UserStatus.ACTIVE);

        given(userRepository.save(any(UserEntity.class))).willReturn(userEntity);

        // when
        UserResponse userResponse = userService.signUp(request);

        // then
        assertThat(userResponse.getEmail()).isEqualTo("user@test.com");
        assertThat(userResponse.getName()).isEqualTo("testName");
        assertThat(userResponse.getAddress()).isEqualTo("testAddress");
        assertThat(userResponse.getPhone()).isEqualTo("01012345600");

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("회원가입 실패_이메일 중복")
    void sign_emailDuplicated(){

        // given
        UserRequest request = new UserRequest(
                "user@test.com", "123456", "testName", "testAddress", "01012345600");

        given(userQueryRepository.existsByEmail(request.getEmail())).willReturn(true);

        // when & then
        assertThatThrownBy(() -> userService.signUp(request))
                .isInstanceOf(UserException.class)
                        .hasMessageContaining(BusinessErrorCode.EMAIL_DUPLICATED.getMessage());

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("회원가입 실패_휴대폰 중복")
    void sign_phoneDuplicated(){

        // given
        UserRequest request = new UserRequest(
                "user@test.com", "123456", "testName", "testAddress", "01012345600");

        given(userQueryRepository.existsByPhone(request.getPhone())).willReturn(true);

        // when & then
        assertThatThrownBy(() -> userService.signUp(request))
                .isInstanceOf(UserException.class)
                        .hasMessageContaining(BusinessErrorCode.PHONE_DUPLICATED.getMessage());

        verify(userRepository, never()).save(any());
    }
}