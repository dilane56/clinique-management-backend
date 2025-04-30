package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class FactureResponseDto {
    private Long id;
    private Double montantTotal;
    private LocalDate dateEmission;
    private String patientUsername;
    private  String description;
}
