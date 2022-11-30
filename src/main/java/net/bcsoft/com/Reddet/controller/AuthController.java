package net.bcsoft.com.Reddet.controller;

import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.RuntimeException;

@RestController
@RequestMapping("/Api/Auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign")
    public ResponseEntity<String> signUp (@RequestBody RegisterRequest registerRequest){
        authService.signUp(registerRequest);
        return new ResponseEntity<>("You have successfully registered.", HttpStatus.OK);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<String> accountVerification(@PathVariable String token){
        authService.accountVerification(token);
        return new ResponseEntity<>("Account successfully verified.", HttpStatus.OK);
}
}
