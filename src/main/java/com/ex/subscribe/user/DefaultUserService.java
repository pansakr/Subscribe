package com.ex.subscribe.user;

import com.ex.subscribe.global.exception.BusinessErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserQueryRepository userQueryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    /**
     * request : 사용자가 입력한 가입 정보
     * DB 조회 후 값이 없다면 가입, 값이 있다면 예외
     */
    @Transactional
    @Override
    public UserResponse signUp(UserRequest request) {

        validate(request);

        return UserMapper.toResponse(
                userRepository.save(UserMapper.toEntity(request, encoder))
        );
    }

    /**
     * 검증 : 사용자가 입력한 가입 정보가 DB에 있으면 true
     */
    private void validate(UserRequest request){

        if (userQueryRepository.existsByEmail(request.getEmail()))
            throw new UserException(BusinessErrorCode.EMAIL_DUPLICATED);

        if (userQueryRepository.existsByPhone(request.getPhone()))
            throw new UserException(BusinessErrorCode.PHONE_DUPLICATED);
    }

}
