package com.ex.subscribe.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    /**
     * 회원 가입 API
     * @param request 사용자가 입력한 가입 정보
     * @return 가입 완료된 회원 정보(json)
     */
    @PostMapping("/api/users")
    public ResponseEntity<UserResponse> signUp(@Validated @RequestBody UserRequest request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(request));
    }

}
