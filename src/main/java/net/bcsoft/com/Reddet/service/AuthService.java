package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.model.User;

public class AuthService {

    public void SignUp(RegisterRequest registerRequest){
        User user = new User();

        user.setUsername("");
    }


}
