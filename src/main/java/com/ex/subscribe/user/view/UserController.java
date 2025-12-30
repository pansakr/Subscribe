package com.ex.subscribe.user.view;

import com.ex.subscribe.user.dto.UserRequest;
import com.ex.subscribe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * signup.html 응답
     * @param userRequest signup.html 의 th:object="${userRequest} 채워주기 위한 값
     * @return
     */
    @GetMapping("/users")
    public String signUpForm(@ModelAttribute("userRequest") UserRequest userRequest){
        return "signup";
    }

    /**
     * 회원가입
     * @param userRequest 가입 정보
     * @return 검증 오류 시 signup.html, 성공 시 index.html 리다이렉트
     */
    @PostMapping("/users")
    public String signUp(@Validated @ModelAttribute("userRequest") UserRequest userRequest,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) return "signup";

        userService.signUp(userRequest);

        redirectAttributes.addFlashAttribute("msgCode", "signup.success");

        return "redirect:/";
    }

    /**
     * login.html 응답
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // 테스트
    @GetMapping("/mypage")
    public String myPage(){
        return "mypage";
    }

}
