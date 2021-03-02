package com.hunganh.mymoment.service;

import com.hunganh.mymoment.dto.AuthenticationResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.model.Profile;
import com.hunganh.mymoment.model.User;
import com.hunganh.mymoment.repository.ProfileRepository;
import com.hunganh.mymoment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    public void signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setSaltedPassword(signUpRequest.getPassword());
        userRepository.save(user);

        user = userRepository.findByUsername(user.getUsername());
        Profile profile = new Profile();
        profile.setFirstName(signUpRequest.getFirstName());
        profile.setLastName(signUpRequest.getLastName());
        profile.setGender(signUpRequest.getGender());
        profile.setDateOfBirth(signUpRequest.getDateOfBirth());
        profile.setDateCreated(new Date().getTime());
        profile.setDateUpdated(new Date().getTime());
        profileRepository.save(profile);

//        mailService.sendMail(new NotificationEmail("helo", registerRequest.getEmail(), "content"));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        return new AuthenticationResponse();
    }
}
