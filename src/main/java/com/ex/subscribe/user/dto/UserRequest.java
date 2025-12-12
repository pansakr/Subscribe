package com.ex.subscribe.user.dto;

import com.ex.subscribe.global.validation.ValidEmail;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserRequest {

    @ValidEmail
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 6, max = 20, message = "비밀번호는 6 ~ 20 사이로 입력해 주세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요")
    @Size(max = 50, message = "이름은 50 자 이내로 입력해 주세요")
    private String name;

    @NotBlank(message = "주소를 입력하세요")
    @Size(max = 100, message = "주소는 100 자 이내로 입력해 주세요")
    private String address;

    @NotBlank(message = "휴대폰 번호를 입력하세요")
    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력해 주세요 (하이픈 '-' 사용 불가)")
    @Pattern(regexp = "^\\d{11}$", message = "휴대폰 번호는 11자리여야 합니다")
    private String phone;

}
