package com.hunganh.mymoment.controller;

import com.hunganh.mymoment.exception.EmailAlreadyExistsException;
import com.hunganh.mymoment.exception.EmailNotExistsException;
import com.hunganh.mymoment.exception.UsernameAlreadyExistsException;
import com.hunganh.mymoment.response.SnwErrorResponse;
import com.hunganh.mymoment.response.SnwSuccessResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.service.AuthService;
import com.hunganh.mymoment.util.TemplateUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequest signUpRequest) {
        try {
            authService.signup(signUpRequest);
            return new ResponseEntity(new SnwSuccessResponse(), OK);
        }catch (EmailNotExistsException e){
            return new ResponseEntity(new SnwErrorResponse(e.getMessage()), PRECONDITION_FAILED);
        }catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e){
            return new ResponseEntity(new SnwErrorResponse(e.getMessage()), CONFLICT);
        }catch (Exception e){
            return new ResponseEntity(new SnwErrorResponse(e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try{
            Map<String, Object> result = authService.login(loginRequest);
            return new ResponseEntity(TemplateUtil.generateJson(result), OK);
        }catch (EmailNotExistsException e){
            return new ResponseEntity(new SnwErrorResponse(e.getMessage()), PRECONDITION_FAILED);
        }
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verify(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity(new SnwSuccessResponse(), OK);
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
