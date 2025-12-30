package com.ex.subscribe.user;

import com.ex.subscribe.global.jpa.JpaConfig;
import com.ex.subscribe.user.entity.UserEntity;
import com.ex.subscribe.user.entity.UserRole;
import com.ex.subscribe.user.entity.UserStatus;
import com.ex.subscribe.user.repository.UserQueryRepository;
import com.ex.subscribe.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserQueryRepository.class, JpaConfig.class})
class UserQueryRepositoryTest {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("이메일이 존재하면 true 반환")
    void existsByEmail_true() {

        // given
        UserEntity userEntity = new UserEntity(
                "user@test.com",
                "encoded_pw",
                "testName",
                "testAddress",
                "01012345678",
                UserRole.ROLE_USER,
                UserStatus.ACTIVE
        );
        userRepository.save(userEntity);

        // when
        boolean exists = userQueryRepository.existsByEmail("user@test.com");

        // then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("이메일이 존재하지 않으면 false 반환")
    void existsByEmail_false() {

        // when
        boolean exists = userQueryRepository.existsByEmail("noUser@test.com");

        // then
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("휴대폰 번호가 존재하면 true 반환")
    void existsByPhone_true() {

        // given
        UserEntity userEntity = new UserEntity(
                "user@test.com",
                "encoded_pw",
                "testName",
                "testAddress",
                "01012345678",
                UserRole.ROLE_USER,
                UserStatus.ACTIVE
        );
        userRepository.save(userEntity);

        // when
        boolean exists = userQueryRepository.existsByPhone("01012345678");

        // then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("휴대폰 번호가 존재하지 않으면 false 반환")
    void existsByPhone_false() {

        boolean exists = userQueryRepository.existsByPhone("01012345678");

        assertThat(exists).isFalse();

    }

}