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
     * 회원가입 기능
     * <p></p>
     * 이메일과 휴대폰 번호가 중복되는지 검증한 뒤,
     * 중복이 없으면 가입을 진행하고, 중복이 있을 경우 있다면 예외를 발생시킨다
     *
     * @param request 가입 요청 정보
     * @return 가입 완료된 회원 정보 응답
     * @throws UserException 이메일 또는 휴대폰 번호가 중복된 경우
     */
    @Transactional
    @Override
    public UserResponse signUp(UserRequest request) {

        validate(request);

        return UserMapper.toResponse(
                userRepository.save(UserMapper.toEntity(request, encoder))
        );
    }

    @Transactional
    @Override
    public EmailCheckResponse checkEmail(String email) {

        if (userQueryRepository.existsByEmail(email))
            throw new UserException(BusinessErrorCode.EMAIL_DUPLICATED);

        return EmailCheckResponse.success();
    }




    /**
     * 회원가입 요청의 이메일과 휴대폰 번호의 중복 여부를 검증
     *
     * @param request 가입 요청 정보
     * @throws UserException 이메일 또는 휴대폰 번호가 중복된 경우
     */
    private void validate(UserRequest request){

        if (userQueryRepository.existsByEmail(request.getEmail()))
            throw new UserException(BusinessErrorCode.EMAIL_DUPLICATED);

        if (userQueryRepository.existsByPhone(request.getPhone()))
            throw new UserException(BusinessErrorCode.PHONE_DUPLICATED);
    }

}
