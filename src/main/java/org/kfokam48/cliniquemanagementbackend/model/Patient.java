package org.kfokam48.cliniquemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Patient extends Utilisateur{

    private Date dateNaissance;
    private String antecedents;
    private String allergies;

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezvous = new ArrayList<>();
}
