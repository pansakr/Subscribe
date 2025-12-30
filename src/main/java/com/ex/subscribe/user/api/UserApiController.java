package com.ex.subscribe.user.api;

import com.ex.subscribe.global.validation.ValidEmail;
import com.ex.subscribe.user.dto.EmailCheckResponse;
import com.ex.subscribe.user.dto.UserRequest;
import com.ex.subscribe.user.dto.UserResponse;
import com.ex.subscribe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

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

    /**
     * 이메일 중복 확인 API
     */
    @GetMapping("/api/users/exists")
    public ResponseEntity<EmailCheckResponse> emailCheck(@RequestParam("email") @ValidEmail String email){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.checkEmail(email));
    }

}
