package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

@Data
public class UtilisateurResponseDTO {
    private Long id;
    private String email;
    private String username;
    private String nom;
    private String prenom;
    private String telephone;
    private String sexe;
    private String adresse;
    private String role;


}
