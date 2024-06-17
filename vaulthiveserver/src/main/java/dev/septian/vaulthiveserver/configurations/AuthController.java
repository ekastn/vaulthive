package dev.septian.vaulthiveserver.configurations;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.septian.vaulthiveserver.domain.dtos.LoginDto;
import dev.septian.vaulthiveserver.domain.dtos.RegisterDto;
import dev.septian.vaulthiveserver.domain.entities.UserEntity;
import dev.septian.vaulthiveserver.domain.responses.AuthResponse;
import dev.septian.vaulthiveserver.services.AuthService;
import dev.septian.vaulthiveserver.services.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto registerUserDto) {
        UserEntity registeredUser = authService.signup(registerUserDto);

        String jwtToken = jwtService.generateToken(registeredUser);

        AuthResponse registerResponse = AuthResponse.builder()
                .id(registeredUser.getId())
                .username(registeredUser.getUsername())
                .email(registerUserDto.getEmail())
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginDto loginUserDto) {
        UserEntity authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthResponse loginResponse = AuthResponse.builder()
                .id(authenticatedUser.getId())
                .username(authenticatedUser.getUsername())
                .email(authenticatedUser.getEmail())
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
