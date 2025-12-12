package com.ex.subscribe.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {})
@Target({FIELD, PARAMETER, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "이메일을 입력하세요")
@Email(message = "이메일 형식이 올바르지 않습니다")
public @interface ValidEmail {

    String message() default "유효하지 않은 이메일입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
