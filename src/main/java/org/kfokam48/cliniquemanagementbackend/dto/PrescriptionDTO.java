package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionDTO {
    private String medicament;
    private Long patientId;
    private Long medecinId;
    private LocalDate datePrescription;

    // Getters et Setters
}
