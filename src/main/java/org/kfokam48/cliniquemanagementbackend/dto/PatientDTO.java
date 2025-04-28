package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO extends UtilisateurDTO {

   private LocalDate dateNaissance;
   private Integer numeroDossierMedical;

    // Getters et Setters
}
