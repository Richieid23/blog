package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.dto.auth.LoginRequest;
import id.web.fitrarizki.blog.dto.auth.LoginResponse;
import id.web.fitrarizki.blog.service.JwtService;
import id.web.fitrarizki.blog.service.MyUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Username or password is incorrect");
        }

        String token = jwtService.generateToken(userDetailsService.loadUserByUsername(loginRequest.getUsername()));
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
