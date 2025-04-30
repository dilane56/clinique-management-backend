package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

@Data
public class MedecinInRendezVousDto {
    private Long id;
    private String username;
    private String email;
    private String specialite;
}
