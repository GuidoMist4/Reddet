package net.bcsoft.com.Reddet.service;

import com.nimbusds.jwt.JWT;
import net.bcsoft.com.Reddet.DTO.LoginRequest;
import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.exception.ExceptionHandler;
import net.bcsoft.com.Reddet.model.NotificationEmail;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.model.VerificationToken;
import net.bcsoft.com.Reddet.repository.UserRepo;
import net.bcsoft.com.Reddet.repository.VerificationTokenRepository;
import net.bcsoft.com.Reddet.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MailService mailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    @Transactional
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepo.findUserByUsername(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }

    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Attiva account", user.getEmail(), "Text email" + "Corpo email" +
                "\n" + "To activate your account click on the link below : " + " \n" + "http://localhost:8080/Api/Auth/verification/" + token));
    }

    @Transactional
    public void accountVerification(String token) {
        Optional<VerificationToken> optionalToken = verificationTokenRepository.findByToken(token);
        optionalToken.orElseThrow(() ->
                new ExceptionHandler("ERROR: Invalid Token."));
        findUserAndAbilitate(optionalToken.get());
    }

    @Transactional
    public void findUserAndAbilitate(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepo.findUserByUsername(username).orElseThrow(() ->
                new ExceptionHandler("ERROR: Username not found."));
        user.setEnabled(true);
        userRepo.save(user);
    }

    @Transactional
    public void login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @Transactional
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

}

