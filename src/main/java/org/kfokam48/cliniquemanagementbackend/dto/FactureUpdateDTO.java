package org.kfokam48.cliniquemanagementbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.kfokam48.cliniquemanagementbackend.enums.ModePayement;
import org.kfokam48.cliniquemanagementbackend.enums.StatutPayement;

import java.time.LocalDateTime;
@Data
public class FactureUpdateDTO {
    @NotNull(message = "Le montant total ne doit pas être nul")
    private double montantTotal;
    private LocalDateTime dateEmission;
    private LocalDateTime datePayement;
    private double montantPayement;
    private double montantRestant;
    private StatutPayement statutPayement;
    private ModePayement modePayement;
    private String description;
    @NotNull(message = "L'id du rendez-vous ne doit pas être nul")
    private Long RendezVousId;
}
