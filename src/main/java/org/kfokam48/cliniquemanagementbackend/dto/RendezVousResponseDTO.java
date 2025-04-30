package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RendezVousResponseDTO {

    private Long id; // Identifiant du rendez-vous
    private LocalDateTime dateRendezVous; // Date du rendez-vous
    private PatientInRendezVousDTO patient; // Référence au Patient
    private MedecinInRendezVousDto  medecin; // Référence au Médecin
    private String motif; // Motif du rendez-vous
}
