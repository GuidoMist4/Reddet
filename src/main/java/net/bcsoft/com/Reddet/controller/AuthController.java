package net.bcsoft.com.Reddet.controller;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/Auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign")
    public ResponseEntity<String> SignUp (@RequestBody RegisterRequest registerRequest){
        authService.SignUp(registerRequest);
        return new ResponseEntity<>("You have succesfully registered.", HttpStatus.OK);
    }
}
