package com.ex.subscribe.user.service;

import com.ex.subscribe.user.dto.EmailCheckResponse;
import com.ex.subscribe.user.dto.UserRequest;
import com.ex.subscribe.user.dto.UserResponse;

public interface UserService {

    UserResponse signUp(UserRequest request);

    EmailCheckResponse checkEmail(String email);
}
