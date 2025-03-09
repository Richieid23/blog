package id.web.fitrarizki.blog.controller;

import id.web.fitrarizki.blog.config.Bucket4jConfig;
import id.web.fitrarizki.blog.dto.auth.LoginRequest;
import id.web.fitrarizki.blog.dto.auth.LoginResponse;
import id.web.fitrarizki.blog.exception.ApiException;
import id.web.fitrarizki.blog.service.JwtService;
import id.web.fitrarizki.blog.service.MyUserDetailsService;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    private final Bucket4jConfig bucket4jConfig;
//    private final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
//        Bucket loginBucket = bucketMap.computeIfAbsent(loginRequest.getUsername(), k -> bucket4jConfig.loginBucket());

        Bucket loginBucket = bucket4jConfig.loginBucket(loginRequest.getUsername());

        if (!loginBucket.tryConsume(1)) {
            throw new ApiException("Too many request", HttpStatus.TOO_MANY_REQUESTS);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Username or password is incorrect");
        }

        String token = jwtService.generateToken(userDetailsService.loadUserByUsername(loginRequest.getUsername()));
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
