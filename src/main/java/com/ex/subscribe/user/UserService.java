package com.ex.subscribe.user;

public interface UserService {

    UserResponse signUp(UserRequest request);

    EmailCheckResponse checkEmail(String email);
}
