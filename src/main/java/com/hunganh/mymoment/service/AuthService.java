package com.hunganh.mymoment.service;

import com.hunganh.mymoment.base.AssocBaseRepository;
import com.hunganh.mymoment.dto.AuthenticationResponse;
import com.hunganh.mymoment.dto.LoginRequest;
import com.hunganh.mymoment.dto.SignUpRequest;
import com.hunganh.mymoment.exception.EmailAlreadyExistsException;
import com.hunganh.mymoment.exception.EmailNotExistsException;
import com.hunganh.mymoment.exception.MyMomentsException;
import com.hunganh.mymoment.exception.UsernameAlreadyExistsException;
import com.hunganh.mymoment.helper.MailHelper;
import com.hunganh.mymoment.model.object.NotificationEmail;
import com.hunganh.mymoment.model.object.Profile;
import com.hunganh.mymoment.model.object.User;
import com.hunganh.mymoment.model.object.VerificationToken;
import com.hunganh.mymoment.repository.ProfileRepository;
import com.hunganh.mymoment.repository.UserRepository;
import com.hunganh.mymoment.repository.VerificationTokenRepository;
import com.hunganh.mymoment.repository.assoc.VerificationOwnershipRepository;
import com.hunganh.mymoment.security.JwtProvider;
import com.sn.appbase.constant.SnwAssocType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.*;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationOwnershipRepository verificationOwnershipRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final AssocBaseRepository assocBaseRepository;

    public void signup(SignUpRequest signUpRequest) {
        log.info("registering user {}", signUpRequest.getUsername());

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .saltedPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .lastIp("")
                .enabled(true)
                .build();
        boolean l1 = userRepository.existsByUsername(user.getUsername());
        Optional<User> l2 = userRepository.existsForUsername(user.getUsername());
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            log.warn("username {} already exists.", user.getUsername());
//            throw new UsernameAlreadyExistsException(
//                    String.format("username %s already exists", user.getUsername()));
//        }
//        if (userRepository.existsByEmail(user.getEmail())) {
//            log.warn("email {} already exists.", user.getEmail());
//            throw new EmailAlreadyExistsException(
//                    String.format("email %s already exists", user.getEmail()));
//        }
//        if (!MailHelper.isAddressValid(user.getEmail())) {
//            log.warn("email {} doesn't exist.", user.getEmail());
//            throw new EmailNotExistsException(
//                    String.format("email %s doesn't exist", user.getEmail()));
//        }
        userRepository.save(user);

        Profile profile = Profile.builder()
                .userId(user.getId())
                .email(signUpRequest.getEmail())
                .fullName(signUpRequest.getFullName())
                .build();
        profileRepository.save(profile);

        user.setProfile(profile);

        VerificationToken verificationToken = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Activate your Account",
                profile.getEmail(), "Thank you for signing up to My Moments with username <b>" + user.getUsername() + "</b>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/api/auth/verify/" + verificationToken.getToken()));
        assocBaseRepository.addAssoc(user, SnwAssocType.HAS_VERTIFICATION_TOKEN, verificationToken, "", new Date().getTime());

    }

    public Map<String, Object> login(LoginRequest loginRequest) {
        Map<String, Object> result = new HashMap<>();
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        result.put("Login", new AuthenticationResponse(token, loginRequest.getUsername()));
        return result;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new MyMomentsException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getVerificationOwnerships().iterator().next().getStartNode().getUsername();
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
