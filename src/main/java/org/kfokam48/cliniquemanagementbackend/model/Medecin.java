package org.kfokam48.cliniquemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrimaryKeyJoinColumn(name = "utilisateurs_id")
public class Medecin extends Utilisateur{
    private String specialite;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezvous = new ArrayList<>();
}
