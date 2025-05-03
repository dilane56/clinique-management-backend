package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;
import org.kfokam48.cliniquemanagementbackend.enums.Sexe;


import java.util.Date;

@Data
public class PatientDTO extends UtilisateurDTO {



   private Date dateNaissance;
   private Integer numeroDossierMedical;

    // Getters et Setters
}
