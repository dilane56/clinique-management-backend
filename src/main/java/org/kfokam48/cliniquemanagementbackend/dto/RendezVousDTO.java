package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RendezVousDTO {

    private LocalDate  dateRendezVous; // Date du rendez-vous
    private Long patientId; // Référence au Patient
    private Long medecinId; // Référence au Médecin
    private String motif; // Motif du rendez-vous

    // Getters et Setters
}

