package com.rk.az.service;

import com.rk.az.domain.USER_ROLE;
import com.rk.az.request.LoginRequest;
import com.rk.az.response.AuthResponse;
import com.rk.az.response.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;

    AuthResponse signing(LoginRequest req) throws Exception;
}
