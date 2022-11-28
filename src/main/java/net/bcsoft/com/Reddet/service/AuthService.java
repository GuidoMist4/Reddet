package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.model.User;

import java.time.Instant;

public class AuthService {

    public void SignUp(RegisterRequest registerRequest){
        User user = new User();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        user.setCreated(Instant.now());
    }


}
