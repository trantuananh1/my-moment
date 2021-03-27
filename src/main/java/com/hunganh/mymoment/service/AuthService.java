package com.hunganh.mymoment.service;

import com.hunganh.mymoment.dto.AuthenticationResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.exception.MyMomentsException;
import com.hunganh.mymoment.model.NotificationEmail;
import com.hunganh.mymoment.model.Profile;
import com.hunganh.mymoment.model.User;
import com.hunganh.mymoment.model.VerificationToken;
import com.hunganh.mymoment.model.assoc.VerificationOwnership;
import com.hunganh.mymoment.repository.ProfileRepository;
import com.hunganh.mymoment.repository.UserRepository;
import com.hunganh.mymoment.repository.VerificationTokenRepository;
import com.hunganh.mymoment.repository.assoc.VerificationOwnershipRepository;
import com.hunganh.mymoment.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationOwnershipRepository verificationOwnershipRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setSaltedPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setLastIp("");
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setUserId(user.getId());
        profile.setEmail(signUpRequest.getEmail());
        profile.setFullName(signUpRequest.getFullName());
        profileRepository.save(profile);

        user.setProfile(profile);

        VerificationToken verificationToken = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Activate your Account",
                profile.getEmail(), "Thank you for signing up to My Moments with username <b>"+user.getUsername()+"</b>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/api/auth/verify/" + verificationToken.getToken()));

        VerificationOwnership verificationOwnership =new VerificationOwnership();
        verificationOwnership.setStartNode(user);
        verificationOwnership.setEndNode(verificationToken);
        verificationOwnership.setTime(new Date().getTime());
        verificationOwnershipRepository.save(verificationOwnership);
        if (user.getVerificationOwnerships()==null){
            user.setVerificationOwnerships(new HashSet<>());
        }
        user.getVerificationOwnerships().add(verificationOwnership);
        userRepository.save(user);
        verificationToken.setVerificationOwnerships(verificationOwnership);
        verificationTokenRepository.save(verificationToken);
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
        String username = verificationToken.getVerificationOwnerships().getStartNode().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new MyMomentsException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private VerificationToken generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);

        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
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
