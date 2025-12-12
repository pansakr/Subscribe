package com.ex.subscribe.user;

import com.ex.subscribe.global.exception.BusinessErrorCode;
import com.ex.subscribe.global.exception.GlobalApiExceptionHandler;
import com.ex.subscribe.user.api.UserApiController;
import com.ex.subscribe.user.dto.UserRequest;
import com.ex.subscribe.user.dto.UserResponse;
import com.ex.subscribe.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserApiController.class)
@Import(GlobalApiExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[컨트롤러] 회원가입 성공")
    void signUp_success() throws Exception {

        // given
        UserRequest request = new UserRequest(
                "user@test.com",
                "123456",
                "testName",
                "testAddress",
                "01012345600"
        );

        UserResponse response = new UserResponse(
                "user@test.com",
                "testName",
                "testAddress",
                "01012345600"
        );

        given(userService.signUp(any(UserRequest.class))).willReturn(response);

        String json = objectMapper.writeValueAsString(request);

        // when & then
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("user@test.com"))
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.address").value("testAddress"))
                .andExpect(jsonPath("$.phone").value("01012345600"));

    }

    @Test
    @DisplayName("[컨트롤러] 회원가입 실패")
    void signUp_failed() throws Exception{

        UserRequest request = new UserRequest(
                "user@test.com",
                "123456",
                "testName",
                "testAddress",
                "01011112222"
        );

        given(userService.signUp(any(UserRequest.class)))
                .willThrow(new UserException(BusinessErrorCode.EMAIL_DUPLICATED));

        String json = objectMapper.writeValueAsString(request);

        // when & then
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(BusinessErrorCode.EMAIL_DUPLICATED.getErrorCode()))
                .andExpect(jsonPath("$.message").value(BusinessErrorCode.EMAIL_DUPLICATED.getMessage()));
    }
}