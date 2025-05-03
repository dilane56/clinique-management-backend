package org.kfokam48.cliniquemanagementbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kfokam48.cliniquemanagementbackend.enums.ModePayement;
import org.kfokam48.cliniquemanagementbackend.enums.StatutPayement;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montantTotal;
    private LocalDate dateEmission;
    private Date datePayement;
    private double montantPayement;
    private double montantRestant;
    private StatutPayement statutPayement;
    private ModePayement modePayement;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
