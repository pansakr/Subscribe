package com.ex.subscribe.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식이 올바르지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 6, max = 20, message = "비밀번호는 6 ~ 20 사이로 입력해 주세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요")
    private String name;

    @NotBlank(message = "주소를 입력하세요")
    // @Max(50)
    private String address;

    @NotBlank(message = "휴대폰 번호를 입력하세요")
    @Pattern(
            regexp = "^010\\d{8}$",
            message = "하이픈(-) 을 빼고 입력하세요"
    )
    private String phone;

}
