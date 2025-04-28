package org.kfokam48.cliniquemanagementbackend.auth.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String role;
    // + Getters/Setters
}
