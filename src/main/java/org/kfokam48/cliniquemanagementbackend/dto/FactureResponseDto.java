package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;
import org.kfokam48.cliniquemanagementbackend.enums.ModePayement;
import org.kfokam48.cliniquemanagementbackend.enums.StatutPayement;

import java.time.LocalDate;
import java.util.Date;

@Data
public class FactureResponseDto {
    private Long id;
    private Double montantTotal;
    private LocalDate dateEmission;
    private String patientUsername;
    private String rendezvousMotif;
    private Date datePayement;
    private double montantVerser;
    private double montantRestant;
    private StatutPayement statutPayement;
    private ModePayement modePayement;
}
