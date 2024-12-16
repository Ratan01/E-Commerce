package com.rk.az.controller;

import com.rk.az.domain.USER_ROLE;
import com.rk.az.model.User;
import com.rk.az.model.VerificationCode;
import com.rk.az.repository.UserRepository;
import com.rk.az.request.LoginOtpRequest;
import com.rk.az.request.LoginRequest;
import com.rk.az.response.ApiResponse;
import com.rk.az.response.AuthResponse;
import com.rk.az.response.SignupRequest;
import com.rk.az.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt=authService.createUser(req);

        AuthResponse res= new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOtp(req.getEmail(), req.getRole());

        ApiResponse res= new ApiResponse();

        res.setMesssage("otp sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse=authService.signing(req);

        return ResponseEntity.ok(authResponse);
    }
}
