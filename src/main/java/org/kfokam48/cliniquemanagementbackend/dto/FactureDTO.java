package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FactureDTO {
    private Double montant;
    private LocalDate date;
    private Long patientId;

    // Getters et Setters
}
