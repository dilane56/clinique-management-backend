package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RendezVousInUserDto {
    private Long id;
    private LocalDateTime dateRendezVous; // Date du rendez-vous
    private String patientUsername; // Référence au Patient
    private String medecinUsername; // Référence au Médecin
    private String motif;
}
