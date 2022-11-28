package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.repository.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.time.Instant;

public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UtenteRepo utenteRepo;

    @Transactional
    public void SignUp(RegisterRequest registerRequest){

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        utenteRepo.save(user);

    }




}
