package com.ex.subscribe.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    public static UserEntity toEntity(UserRequest request, PasswordEncoder encoder){
        return new UserEntity(
                request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getName(),
                request.getAddress(),
                request.getPhone(),
                UserRole.USER,
                UserStatus.ACTIVE
        );
    }

    public static UserResponse toResponse(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getPhone()
        );
    }
}
