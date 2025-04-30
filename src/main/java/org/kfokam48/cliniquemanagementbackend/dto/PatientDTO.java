package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;


import java.util.Date;

@Data
public class PatientDTO extends UtilisateurDTO {

   private Date dateNaissance;
   private Integer numeroDossierMedical;

    // Getters et Setters
}
