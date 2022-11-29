package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.model.NotificationEmail;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.model.VerificationToken;
import net.bcsoft.com.Reddet.repository.UserRepo;
import net.bcsoft.com.Reddet.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
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
    public void SignUp(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
        String token = GenerateVerificationToken(user);
        mailService.SendMail(new NotificationEmail("Attiva account", user.getEmail(),"Text email"+ "Corpo email"+ token));
    }

    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    private String GenerateVerificationToken(User user){
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        return token;
    }
}
