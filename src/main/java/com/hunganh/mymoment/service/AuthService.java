package com.hunganh.mymoment.service;

import com.hunganh.mymoment.dto.AuthenticationResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.exception.MyMomentsException;
import com.hunganh.mymoment.model.NotificationEmail;
import com.hunganh.mymoment.model.Profile;
import com.hunganh.mymoment.model.User;
import com.hunganh.mymoment.model.VerificationToken;
import com.hunganh.mymoment.repository.ProfileRepository;
import com.hunganh.mymoment.repository.UserRepository;
import com.hunganh.mymoment.repository.VerificationTokenRepository;
import com.hunganh.mymoment.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setSaltedPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setDateCreated(new Date().getTime());
        user.setDateUpdated(new Date().getTime());
        user.setLastIp("");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setUserId(user.getId());
        profile.setEmail(signUpRequest.getEmail());
        profile.setFullName(signUpRequest.getFullName());
        profileRepository.save(profile);

        user.hasProfile(profile);
        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Activate your Account",
                profile.getEmail(), "Thank you for signing up to My Moments with username <b>"+user.getUsername()+"</b>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/api/auth/verify/" + token));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new MyMomentsException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new MyMomentsException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }


    public Map<String, Object> attempt(SignUpRequest signUpRequest) {
        Map<String, Object> result=new HashMap<>();
        List<User> users=userRepository.findAll();
        result.put("as", "as");
        result.put("User", users);
        return result;
    }
}
