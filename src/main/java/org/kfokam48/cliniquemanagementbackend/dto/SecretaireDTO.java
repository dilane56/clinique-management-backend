package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;
import org.kfokam48.cliniquemanagementbackend.enums.Roles;

@Data
public class SecretaireDTO  {
    private String username;
    private String password;
    private String email;
    private Roles role;

    // Getters et Setters
}
