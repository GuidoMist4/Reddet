package net.bcsoft.com.Reddet.controller;

import net.bcsoft.com.Reddet.DTO.AuthenticationResponse;
import net.bcsoft.com.Reddet.DTO.LoginRequest;
import net.bcsoft.com.Reddet.DTO.RefreshTokenRequest;
import net.bcsoft.com.Reddet.DTO.RegisterRequest;
import net.bcsoft.com.Reddet.service.AuthService;
import net.bcsoft.com.Reddet.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.RuntimeException;

@RestController
@RequestMapping("/Api/Auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;

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

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.loginAuth(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
    }
}
