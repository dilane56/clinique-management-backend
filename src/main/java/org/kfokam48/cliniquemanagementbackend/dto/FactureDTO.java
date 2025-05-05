package org.kfokam48.cliniquemanagementbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FactureDTO {
    @NotNull(message = "Le montant total ne doit pas être nul")
    private Double montantTotal;
    @NotNull(message = "L'id du rendez-vous ne doit pas être nul")
    private Long rendezVousId;

    // Getters et Setters
}
