package com.hunganh.mymoment.controller;

import com.hunganh.mymoment.dto.AuthenticationResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequest signUpRequest) {
        authService.signup(signUpRequest);
        return new ResponseEntity<>("Success", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verify(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/attempt")
    public ResponseEntity attemptSignUp(@RequestBody SignUpRequest signUpRequest) {
//        Map<String, Object> result;
//        result = authService.attempt(signUpRequest);
//        return new ResponseEntity(result, OK);
        return new ResponseEntity(OK);
    }

    @PostMapping("/send_email")
    public ResponseEntity<String> sendEmail() {
        return new ResponseEntity<>("", OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("", OK);
    }

    @PostMapping("/refresh/token")
    public boolean refreshTokens() {
        return true;
    }
}
