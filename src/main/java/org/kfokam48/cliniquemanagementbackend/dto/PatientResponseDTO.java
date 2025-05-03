package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;
import org.kfokam48.cliniquemanagementbackend.enums.Roles;
import org.kfokam48.cliniquemanagementbackend.enums.Sexe;


import java.util.Date;
import java.util.List;

@Data
public class PatientResponseDTO extends UtilisateurDTO{
    private Date dateNaissance;
    private Integer numeroDossierMedical;
    private List<RendezVousInUserDto> rendezvous;
    private Roles role;
    private Sexe sexe;
    private long id;
}
