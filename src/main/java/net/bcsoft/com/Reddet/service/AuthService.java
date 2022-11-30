package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.controller.exception.ExceptionHandler;
import net.bcsoft.com.Reddet.model.NotificationEmail;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.model.VerificationToken;
import net.bcsoft.com.Reddet.repository.UserRepo;
import net.bcsoft.com.Reddet.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.lang.RuntimeException;
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

    @Transactional
    public void signUp(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Attiva account", user.getEmail(),"Text email"+ "Corpo email"+
                "\n" + "To activate your account click on the link below : " + " \n" + "http://localhost:8080/Api/Auth/verification/"+ token ));
    }

    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    private String generateVerificationToken(User user){
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Transactional
    public void accountVerification(String token){
        Optional<VerificationToken> optionalToken = verificationTokenRepository.findByToken(token);
        optionalToken.orElseThrow(() ->
        new ExceptionHandler("ERROR: Invalid Token."));
        findUserAndAbilitate(optionalToken.get());
    }

    @Transactional
    public void findUserAndAbilitate(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = new User();
        userRepo.findUserByUsername(username).orElseThrow(() ->
            new ExceptionHandler("ERROR: Username not found."));
        user.setEnabled(true);
        userRepo.save(user);
    }

}

