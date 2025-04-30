package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.util.List;
@Data
public class MedecinResponseDTO extends UtilisateurDTO {
    private long id;
    private List<RendezVousInUserDto> rendezvous;
    private String specialite;
}
