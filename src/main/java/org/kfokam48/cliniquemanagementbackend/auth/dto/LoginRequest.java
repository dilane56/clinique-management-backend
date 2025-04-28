package org.kfokam48.cliniquemanagementbackend.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    // + Getters/Setters
}
