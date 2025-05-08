package org.kfokam48.cliniquemanagementbackend.controlleur.auth;

import jakarta.validation.Valid;

import org.kfokam48.cliniquemanagementbackend.dto.auth.LoginRequest;
import org.kfokam48.cliniquemanagementbackend.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@Valid @RequestBody LoginRequest authRequest) {
        try {
            String token = authService.authenticateUser(authRequest);
            String role = String.valueOf(authService.getUserRole(authRequest));
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put ("role", role);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return ResponseEntity.status(401).body(error);
        }


    }
}