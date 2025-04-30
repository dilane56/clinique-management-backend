package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionResponseDTO {
    private Long id;
    private String medicament;
    private String patientUsername;
    private String medecinUsername;
    private String instructions;

    // Getters et Setters
}
