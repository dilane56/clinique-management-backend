package org.kfokam48.cliniquemanagementbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FactureDTO {
    @NotNull(message = "Le montant total ne doit pas être nul")
    private Double montantTotal;
    @NotNull(message = "Le nom du patient ne doit pas être nul")
    private String patientUsername;

    // Getters et Setters
}
