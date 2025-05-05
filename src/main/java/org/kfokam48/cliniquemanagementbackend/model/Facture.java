package org.kfokam48.cliniquemanagementbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kfokam48.cliniquemanagementbackend.enums.ModePayement;
import org.kfokam48.cliniquemanagementbackend.enums.StatutPayement;


import java.time.LocalDateTime;
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
    private LocalDateTime dateEmission;
    private LocalDateTime datePayement;
    private double montantPayement;
    private double montantRestant;
    private StatutPayement statutPayement;
    private ModePayement modePayement;
    private String description;

    @ManyToOne
    @JoinColumn(name = "rendezvous_id")
    private RendezVous rendezVous;

    public void calculerMontantRestant() {
        this.montantRestant = this.montantTotal - this.montantPayement;
    }

}
