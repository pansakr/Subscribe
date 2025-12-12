package com.ex.subscribe.user;

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
     * 회원가입 화면 html 응답
     * @param userRequest
     * @return
     */
    @GetMapping("/users")
    public String signUpForm(@ModelAttribute("userRequest") UserRequest userRequest){
        return "signup";
    }

    /**
     * 회원가입
     * @param userRequest 가입 정보
     * @return 검증 오류 시 signup.html, 성공 시 index.html 응답
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
}
