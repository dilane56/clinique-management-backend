package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class PatientResponseDTO extends UtilisateurDTO{
    private Date dateNaissance;
    private Integer numeroDossierMedical;
    private List<RendezVousInUserDto> rendezvous;
    private long id;
}
