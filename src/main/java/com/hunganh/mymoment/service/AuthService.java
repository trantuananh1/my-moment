package com.hunganh.mymoment.service;

import com.hunganh.mymoment.dto.AuthenticationResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.model.Profile;
import com.hunganh.mymoment.model.User;
import com.hunganh.mymoment.repository.ProfileRepository;
import com.hunganh.mymoment.repository.UserRepository;
import com.hunganh.mymoment.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setSaltedPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setDateCreated(new Date().getTime());
        user.setDateUpdated(new Date().getTime());
        user.setLastIp("");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setUserId(user.getId());
        profile.setFirstName(signUpRequest.getFirstName());
        profile.setLastName(signUpRequest.getLastName());
        profile.setGender(signUpRequest.getGender());
        profile.setDateOfBirth(signUpRequest.getDateOfBirth());
        profileRepository.save(profile);

        user.hasProfile(profile);
        userRepository.save(user);
//        mailService.sendMail(new NotificationEmail("helo", registerRequest.getEmail(), "content"));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }
    @Bean
    CommandLineRunner demo(UserRepository userRepository) {
        return args -> {

//            userRepository.deleteAll();

//            User greg = new User();
//            greg.setUsername("anhtrt");
//            User roy = new User();
//            roy.setUsername("roy");
//            User craig = new User();
//            craig.setUsername("craig");
//
//            userRepository.save(greg);
//            userRepository.save(roy);
//            userRepository.save(craig);

        };
    }
}
